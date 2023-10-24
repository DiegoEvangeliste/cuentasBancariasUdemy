package com.example.cuentas.service;

import com.example.cuentas.model.Account;

import java.util.List;

public interface IAccountService {

    List<Account> findAllAccounts();

    Account findAccountById(Integer idAccount);

    void saveAccount(Account account);

    void deleteAccount(Account account);

}
