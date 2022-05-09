package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.beans.OperationTO;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Transfer;
import com.example.demo.services.TransferService;

@RestController
@CrossOrigin(value="*")
@RequestMapping("/transfer")
public class TransferController {
	
	@Autowired
	private TransferService transferService;

	@PostMapping("/do")
	public ResponseEntity<ResponseTO<Boolean>> doTranfer(@RequestBody() OperationTO operation) {
		return transferService.doTranfer(operation);
	}	
}
