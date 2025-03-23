package com.revature.repos;

import com.revature.models.Orders;

import java.util.List;

public interface OrdersDAO extends GeneralDAO <Orders>{

//    Account createAccount(Account account);
//
//    List<Account> getAllAccounts();
//
//    Account getAccountById(int accountId);
//
//    Account updateAccount(Account account);
//
//    boolean deleteAccountById(int accountId);

    // Other methods we might want
    // Maybe I want to get all of the accounts for a specific user
    List<Orders> getAllByUserId(int userId);
}