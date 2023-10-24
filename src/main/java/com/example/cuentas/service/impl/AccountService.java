package com.example.cuentas.service.impl;

import com.example.cuentas.model.Account;
import com.example.cuentas.repository.AccountRepository;
import com.example.cuentas.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired(required = true)
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account findAccountById(Integer idAccount) {
        return accountRepository.findById(idAccount).orElse(null);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }

}
