package com.example.demo.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Customer;

public interface ICustomer extends JpaRepository<Customer, Long> {
	
	@Query("SELECT a FROM Customer a WHERE a.username = ?1")
	List<Customer> doLogin(String username);
	
}
