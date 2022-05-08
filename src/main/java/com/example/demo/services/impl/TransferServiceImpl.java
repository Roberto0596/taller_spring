package com.example.demo.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IAccount;
import com.example.demo.DAO.IWithDraw;
import com.example.demo.Utils.Helper;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Transfer;
import com.example.demo.services.TransferService;

@Service
public class TransferServiceImpl implements TransferService {

    private static final Logger log = LoggerFactory.getLogger(WithdrawServiceImpl.class);
	
	@Autowired
	private static IAccount iaccount;
	
	@Override
	public ResponseEntity<ResponseTO<Boolean>> doTranfer(Transfer transfer) {
		log.info("Iniciando retiro de dinero para la cuenta: {}", transfer.getAccount());
		double originalBalance = transfer.getAccount().getBalance();
		ResponseEntity<ResponseTO<Boolean>> response = Helper.doWithdraw(transfer.getAccount(), transfer.getAmount());
		
		if(response.getBody().getStatus() == -1) {
			log.info("Se reversará el saldo");
			reverse(transfer, originalBalance);
		}
		
		return response;
	}

	@Override
	public void reverse(Transfer transfer, double realBalance) {
		try {
			Account account = transfer.getAccount();
			account.setBalance(realBalance);
			iaccount.save(account);
			log.info("Saldo reversado con exito");
		} catch(Exception e) {
			log.info("No fue posible reversar el monto {}", e.getMessage());
		}
	}

}
