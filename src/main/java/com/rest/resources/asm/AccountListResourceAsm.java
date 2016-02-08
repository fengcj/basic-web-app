package com.rest.resources.asm;

import com.core.services.util.AccountList;
import com.rest.mvc.AccountController;
import com.rest.resources.AccountListResource;
import com.rest.resources.AccountResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

/**
 * Created by fcj on 16/2/7.
 */
public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList,AccountListResource>{


    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(AccountList accountList) {
        List<AccountResource> resList = new AccountResourceAsm().toResources(accountList.getAccounts());
        AccountListResource finalRes = new AccountListResource();
        finalRes.setAccounts(resList);
        return finalRes;
    }
}
