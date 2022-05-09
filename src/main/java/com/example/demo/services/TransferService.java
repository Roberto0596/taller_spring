package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.beans.OperationTO;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Transfer;

public interface TransferService {
	void reverse(Transfer transfer, double realBalance);
	ResponseEntity<ResponseTO<Boolean>> doTranfer(OperationTO operation);
}
