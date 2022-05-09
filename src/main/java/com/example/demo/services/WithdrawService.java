package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.beans.OperationTO;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Withdraw;

public interface WithdrawService {
	ResponseEntity<ResponseTO<Boolean>> doWithdraw(OperationTO operation);
	void reverse(Withdraw withdraw, double realBalance);
}
