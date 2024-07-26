package com.example.banking.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.banking.entity.Account;
import com.example.banking.repo.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepo accountRepo;
	
	@Autowired
	public AccountServiceImpl(AccountRepo accountRepo) {
		super();
		this.accountRepo = accountRepo;
	}

	@Override
	public List<Account> findAll() {
		
		return accountRepo.findAll();
	}

	@Override
	public Account findById(long id) {
		Optional<Account> result=accountRepo.findById(id);
		Account account;
		if(result.isPresent())
			account=result.get();
		else
			throw new RuntimeException("accout not found with id = "+id);
		
		return account;
	}

	@Override
	public void save(Account account) {
	  
		accountRepo.save(account);
		
	}

	
	
	@Override
	public void updateAccount(long id,Account account) {
		Optional<Account> result=accountRepo.findById(id);
		Account existingAccount;
		if(result.isPresent()) {
			existingAccount=result.get();
			existingAccount.setAccountHolderName(account.getAccountHolderName());
			existingAccount.setBalance(account.getBalance());
			accountRepo.save(existingAccount);
		}
		else
			throw new RuntimeException("Account doesn't exist with id = "+id);
	}

	@Override
	public void deleteById(long id) {
		
		Account account=findById(id);
		if(account!=null)
			accountRepo.deleteById(id);
		
	}
    @Override
	public Account addAmount(long id,double amount) {
		Account acc=accountRepo.findById(id).orElseThrow(()-> new RuntimeException("account doesn't exist"));
		
		double newBalance=acc.getBalance()+amount;
		
		acc.setBalance(newBalance);
		
		accountRepo.save(acc);
		
		return acc;
	}
    
    @Override
    public Account substractAmount(long id,double amount) {
    	Optional<Account> result=accountRepo.findById(id);
    	result.orElseThrow(()->new RuntimeException("Account doesn't exist"));
    	Account acc=result.get();
    	if(acc.getBalance()<amount) {
    		throw new RuntimeException("Insufficient Balance");
    	}
    	
    	double newAmount = acc.getBalance()-amount;
    	acc.setBalance(newAmount);
    	
    	accountRepo.save(acc);
    	
    	return acc;
    	 
    	
    }
	
}
