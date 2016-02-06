package com.core.services;

import com.core.models.entries.Account;
import com.core.models.entries.Blog;

/**
 * Created by fcj on 16/1/12.
 */
public interface AccountService {

    Account findAccount(Long id);
    Account createAccount(Account account);
    Blog createBlog(Long accountId,Blog data);

}
