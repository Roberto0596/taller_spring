package com.example.demo.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IAccount;
import com.example.demo.Utils.Helper;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Withdraw;
import com.example.demo.services.WithdrawService;

@Service
public class WithdrawServiceImpl implements WithdrawService {
	
	private static final Logger log = LoggerFactory.getLogger(WithdrawServiceImpl.class);
	
	@Autowired
	private static IAccount iaccount;
	
	@Override
	public ResponseEntity<ResponseTO<Boolean>> doWithdraw(Withdraw withdraw) {
		log.info("Iniciando retiro de dinero para la cuenta: {}", withdraw.getAccount());
		double originalBalance = withdraw.getAccount().getBalance();
		ResponseEntity<ResponseTO<Boolean>> response = Helper.doWithdraw(withdraw.getAccount(), withdraw.getAmount());
		
		if(response.getBody().getStatus() == -1) {
			log.info("Se reversará el saldo");
			reverse(withdraw, originalBalance);
		}
		
		return response;
	}

	@Override
	public void reverse(Withdraw withdraw, double realBalance) {
		try {
			Account account = withdraw.getAccount();
			account.setBalance(realBalance);
			iaccount.save(account);
			log.info("Saldo reversado con exito");
		} catch(Exception e) {
			log.info("No fue posible reversar el monto {}", e.getMessage());
		}
	}

}
