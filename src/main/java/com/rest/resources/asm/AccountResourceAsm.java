package com.rest.resources.asm;

import com.core.entities.Account;
import com.rest.mvc.AccountController;
import com.rest.resources.AccountResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by fengc on 1/13/2016.
 */
public class AccountResourceAsm extends ResourceAssemblerSupport<Account,AccountResource>{

    public AccountResourceAsm() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account account) {

        AccountResource accountResource = new AccountResource();
        accountResource.setName(account.getName());
        accountResource.setPassword(account.getPassword());
        // methodOn(AccountController.class).getAccount(account.getId());
        accountResource.add(linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel());


        return accountResource;
    }
}
