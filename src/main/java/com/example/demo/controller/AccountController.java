package com.example.demo.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Account;
import com.example.demo.services.AccountService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/getAccounts/{idAccount}")
	public ResponseEntity<ResponseTO<List<Account>>> getAccount(
			@PathVariable(value = "idAccount") String idAccount) {
		return accountService.getAccounts(idAccount);
	}
	
	@GetMapping("/getAccountById/{idAccount}")
	public ResponseEntity<ResponseTO<Account>> getAccountById(
			@PathVariable(value = "idAccount") long idAccount) {
		return accountService.getAccount(idAccount);
	}
	
	@PostMapping("/save")
	public ResponseEntity<ResponseTO<Account>> save(
			@RequestBody() Account account) {
		return accountService.save(account);
	}
}
