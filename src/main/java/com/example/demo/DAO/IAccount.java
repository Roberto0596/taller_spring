package com.example.demo.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;

public interface IAccount extends JpaRepository<Account, Long> {
	
	@Query(value = "SELECT a.* FROM account a WHERE a.id_customer = ?1", nativeQuery = true)
	public List<Account> getAccountsByCustomerId(long customer);
}
