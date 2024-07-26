package com.example.banking.controller;

import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.entity.Account;
import com.example.banking.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {

	public AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	@GetMapping("/allaccounts")
	public List<Account> getAll(){
		return accountService.findAll();
	}
//	@GetMapping("/allaccounts")
//	public ResponseEntity<List<Account>> getAll(){
//		List<Account> account=accountService.findAll();
//		return ResponseEntity.ok(account);
//	}
	
	@GetMapping("/account/{accountId}")
	public Account getById(@PathVariable long accountId) {
		return accountService.findById(accountId);
	}
	
	@PostMapping("/account/create")
	public Account creatAccout(@RequestBody Account account) {
		 
		account.setId(0);
		accountService.save(account);
		return account;
	}
	
	@PutMapping("/account/update/{id}")
	public Account updateAccout(@PathVariable long id,@RequestBody Account account) {
		account.setId(id);
		accountService.updateAccount(id,account);
		return account;
	}
	
	@DeleteMapping("/account/{accountId}")
	public String deleteAccountById(@PathVariable long accountId) {
		accountService.deleteById(accountId);
		return "Account deleted successfully";
	}
	
	@PutMapping("/account/{id}/deposit")
	public ResponseEntity<Account> depositAmount(@PathVariable long id,@RequestBody Map<String,Double> request){
		double amount=request.get("amount");
		Account account=accountService.addAmount(id, amount);
		
		return ResponseEntity.ok(account);
	}
	
	@PutMapping("/account/{id}/withdraw")
	public ResponseEntity<Account> withdrawAmount(@PathVariable long id,@RequestBody Map<String,Double> request){
		
		double amount=request.get("amount");
		
        Account account=accountService.substractAmount(id, amount);
        
        return ResponseEntity.ok(account);
        
	}
}
