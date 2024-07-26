package com.example.banking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.banking.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long > {

}
