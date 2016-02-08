package com.core.services;

import com.core.models.entities.Account;
import com.core.models.entities.Blog;
import com.core.services.util.AccountList;
import com.core.services.util.BlogList;

/**
 * Created by fcj on 16/1/12.
 */
public interface AccountService {

    Account findAccount(Long id);
    Account createAccount(Account account);
    Blog createBlog(Long accountId,Blog data);

    BlogList findBlogsByAccount(Long accountId);
    AccountList findAllAccounts();
    Account findByAccountName(String name);



}
