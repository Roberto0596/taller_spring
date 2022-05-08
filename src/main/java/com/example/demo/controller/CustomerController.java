package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.beans.LoginRequestTO;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Customer;
import com.example.demo.services.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins="*")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/login")
	public ResponseEntity<ResponseTO<Customer>> login(
			@RequestBody() LoginRequestTO login) {
		return customerService.login(login);
	}
	
	@PostMapping("/save")
	public ResponseEntity<ResponseTO<Customer>> save(
			@RequestBody() Customer customer) {
		return customerService.save(customer);
	}
}
