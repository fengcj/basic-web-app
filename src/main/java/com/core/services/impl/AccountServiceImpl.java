package com.core.services.impl;

import com.core.models.entities.Account;
import com.core.models.entities.Blog;
import com.core.repositories.AccountRepo;
import com.core.repositories.BlogRepo;
import com.core.services.AccountService;
import com.core.services.exceptions.AccountDoesNotExistException;
import com.core.services.exceptions.AccountExistsException;
import com.core.services.exceptions.BlogExistsException;
import com.core.services.util.AccountList;
import com.core.services.util.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by fcj on 16/2/6.
 */


@Service
@Transactional
public class AccountServiceImpl implements AccountService {



    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private BlogRepo blogRepo;


    @Override
    public Account findAccount(Long id) {
        return accountRepo.findAccount(id);
    }

    @Override
    public Account createAccount(Account data) {
        Account account = accountRepo.findAccountByName(data.getName());
        if(account != null){
            throw new AccountExistsException();
        }
        return accountRepo.createAccount(data);
    }

    @Override
    public Blog createBlog(Long accountId, Blog data) {
        Blog blogSameTitle = blogRepo.findBlogByTitle(data.getTitle());
        if(blogSameTitle != null){
            throw new BlogExistsException();
        }
        Account account = accountRepo.findAccount(accountId);
        if(account == null){
            throw  new AccountDoesNotExistException();
        }
        Blog blog = blogRepo.createBlog(data);
        blog.setOwner(account);
        return blog;
    }

    @Override
    public BlogList findBlogsByAccount(Long accountId) {
        Account account = accountRepo.findAccount(accountId);
        if(account == null){
            throw new AccountDoesNotExistException();
        }
        return new BlogList(blogRepo.findBlogsByAccount(accountId));
    }

    @Override
    public AccountList findAllAccounts() {
        return new AccountList(accountRepo.findAllAccounts());
    }

    @Override
    public Account findByAccountName(String name) {
        return accountRepo.findAccountByName(name);
    }
}
