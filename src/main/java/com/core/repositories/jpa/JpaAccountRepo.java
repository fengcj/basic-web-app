package com.core.repositories.jpa;

import com.core.models.entities.Account;
import com.core.repositories.AccountRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by fcj on 16/2/6.
 */

@Repository
public class JpaAccountRepo implements AccountRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Account> findAllAccounts() {
        Query query = entityManager.createQuery("SELECT a FROM Account a");
        return query.getResultList();
    }

    @Override
    public Account findAccount(Long id) {
        return entityManager.find(Account.class,id);
    }

    @Override
    public Account findAccountByName(String name) {
        Query query = entityManager.createQuery("SELECT a FROM Account a WHERE a.name=?1");
        query.setParameter(1,name);
        List<Account> accounts = query.getResultList();
        if(accounts.isEmpty()){
            return null;
        }else {
            return accounts.get(0);
        }
    }

    @Override
    public Account createAccount(Account account) {
        entityManager.persist(account);
        return account;
    }


}
