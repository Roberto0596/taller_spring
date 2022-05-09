package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.beans.OperationTO;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Withdraw;
import com.example.demo.services.WithdrawService;

@RestController
@CrossOrigin(value="*")
@RequestMapping("/withdraw")
public class WithdrawController {
	
	@Autowired
	private WithdrawService withdrawService;

	@PostMapping("/do")
	public ResponseEntity<ResponseTO<Boolean>> doWithdraw(@RequestBody() OperationTO operation) {
		return withdrawService.doWithdraw(operation);
	}	
}
