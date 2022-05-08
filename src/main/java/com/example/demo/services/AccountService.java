package com.example.demo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Account;

public interface AccountService {
	ResponseEntity<ResponseTO<List<Account>>> getAccounts(String customerId);
	ResponseEntity<ResponseTO<Account>> save(Account account);
	ResponseEntity<ResponseTO<Account>> getAccount(long idAccount);
}
