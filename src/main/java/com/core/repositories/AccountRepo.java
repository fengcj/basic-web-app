package com.core.repositories;

import com.core.models.entities.Account;

import java.util.List;

/**
 * Created by fcj on 16/2/6.
 */
public interface AccountRepo {

    List<Account> findAllAccounts();
    Account findAccount(Long id);
    Account findAccountByName(String name);
    Account createAccount(Account account);


}
