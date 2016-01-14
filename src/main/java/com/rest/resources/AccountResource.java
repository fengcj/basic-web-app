package com.rest.resources;

import com.core.entities.Account;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by fengc on 1/13/2016.
 */
public class AccountResource extends ResourceSupport {

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account toAccount(){
        Account account = new Account();
        account.setName(name);
        account.setPassword(password);
        return account;
    }


}