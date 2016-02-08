package com.rest.mvc;

import com.core.models.entities.Account;
import com.core.models.entities.Blog;
import com.core.services.AccountService;
import com.core.services.exceptions.AccountDoesNotExistException;
import com.core.services.exceptions.AccountExistsException;
import com.core.services.exceptions.BlogExistsException;
import com.core.services.util.AccountList;
import com.core.services.util.BlogList;
import com.rest.exceptions.BadRequestException;
import com.rest.exceptions.ConflictException;
import com.rest.exceptions.NotFoundException;
import com.rest.resources.AccountListResource;
import com.rest.resources.AccountResource;
import com.rest.resources.BlogListResource;
import com.rest.resources.BlogResource;
import com.rest.resources.asm.AccountListResourceAsm;
import com.rest.resources.asm.AccountResourceAsm;
import com.rest.resources.asm.BlogListResourceAsm;
import com.rest.resources.asm.BlogResourceAsm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by fengc on 1/13/2016.
 */

@Controller
@RequestMapping("/rest/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AccountListResource> findAllAccounts(@RequestParam(value="name",required = false) String name){
        AccountList accountList = null;
        if(name == null){
            accountList = accountService.findAllAccounts();
        }else{
            Account account = accountService.findByAccountName(name);
            if(account == null){
                accountList = new AccountList(new ArrayList<Account>());
            }else{
                accountList = new AccountList(Arrays.asList(account));
            }
        }
        AccountListResource accountListResource = new AccountListResourceAsm().toResource(accountList);
     //   System.out.println("===>>>" + accountListResource.toString());
   //     System.out.println("===>>>" + accountListResource.getLinks());

        return new ResponseEntity<AccountListResource>(accountListResource,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sendAccount){
        try{
            Account createdAccount = accountService.createAccount(sendAccount.toAccount());
            AccountResource accountResource = new AccountResourceAsm().toResource(createdAccount);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(accountResource.getLink("self").getHref()));
            return new ResponseEntity<AccountResource>(accountResource,headers,HttpStatus.CREATED);
        }catch (AccountExistsException exception){
            throw new ConflictException(exception);
        }
    }


    @RequestMapping(value="/{accountId}",method = RequestMethod.GET)
    public ResponseEntity<AccountResource> getAccount(@PathVariable Long accountId){
        Account account = accountService.findAccount(accountId);
        if(account == null){
            return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
        }
        AccountResource accountResource = new AccountResourceAsm().toResource(account);
        return new ResponseEntity<AccountResource>(accountResource,HttpStatus.OK);
    }

    @RequestMapping(value = "/{accountId}/blogs",method=RequestMethod.POST)
    public ResponseEntity<BlogResource> createBlog(@PathVariable Long accountId,@RequestBody BlogResource blogResource){
        System.out.println("accountId is " + accountId + "!!!");
        try{
            Blog createdBlog = accountService.createBlog(accountId,blogResource.toBlog());
            BlogResource createdBlogRes = new BlogResourceAsm().toResource(createdBlog);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdBlogRes.getLink("self").getHref()));
            return new ResponseEntity<BlogResource>(createdBlogRes,headers,HttpStatus.CREATED);

        }catch (AccountDoesNotExistException exception){
            throw  new BadRequestException(exception);
        }catch (BlogExistsException exception){
            throw new ConflictException(exception);
        }

    }

    @RequestMapping(value = "/{accountId}/blogs",method=RequestMethod.GET)
    public ResponseEntity<BlogListResource> findAllBlogs(@PathVariable Long accountId){
        try {
            BlogList blogs = accountService.findBlogsByAccount(accountId);
            BlogListResource blogListResource = new BlogListResourceAsm().toResource(blogs);
            return new ResponseEntity<BlogListResource>(blogListResource,HttpStatus.OK);
        } catch (AccountDoesNotExistException e) {
            throw  new NotFoundException();
        }


    }


}
