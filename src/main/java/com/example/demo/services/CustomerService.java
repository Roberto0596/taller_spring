package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.beans.LoginRequestTO;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Customer;

public interface CustomerService {
	ResponseEntity<ResponseTO<Customer>> login(LoginRequestTO loginTo);
	ResponseEntity<ResponseTO<Customer>> save(Customer customer);
}
