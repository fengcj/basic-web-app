package com.core.repositories;

import com.core.models.entities.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * Created by fcj on 16/2/6.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class AccountRepoTest {

    @Autowired
    private AccountRepo accountRepo;

    private Account account;

    @Before
    @Transactional
    @Rollback(false)
    public void setup(){
        account = new Account();
        account.setName("name");
        account.setPassword("password");
        accountRepo.createAccount(account);
    }

    @Test
    @Transactional
    public void testFind(){
        assertNotNull(accountRepo.findAccount(account.getId()));
    }

}
