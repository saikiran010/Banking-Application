package com.example.banking.service;

import java.util.List;

import com.example.banking.entity.Account;

public interface AccountService {
    
	public List<Account> findAll();
	
	public Account findById(long id);
	
	public void save(Account account);
	
	public void updateAccount(long id,Account account);
	
	public void deleteById(long id);
	
	public Account addAmount(long id,double amount);
	
	Account substractAmount(long id,double amount);
	
}
