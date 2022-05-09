package com.example.demo.services.impl;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IAccount;
import com.example.demo.DAO.IMovement;
import com.example.demo.DAO.IWithDraw;
import com.example.demo.Utils.Helper;
import com.example.demo.beans.OperationTO;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Movement;
import com.example.demo.entity.Withdraw;
import com.example.demo.services.WithdrawService;

@Service
public class WithdrawServiceImpl implements WithdrawService {
	
	private static final Logger log = LoggerFactory.getLogger(WithdrawServiceImpl.class);
	
	@Autowired
	private IAccount iaccount;
	@Autowired
	private IWithDraw iwitdraw;
	@Autowired
	private IMovement imovement;
	
	
	@Override
	public ResponseEntity<ResponseTO<Boolean>> doWithdraw(OperationTO operation) {
		Optional<Account> oaccount = iaccount.findById(operation.getAccountId());
		
		if(oaccount.isPresent()) {
			Account account = oaccount.get();
			Withdraw withdraw = Helper.buildWithdrawObject(operation, account);
			log.info("Iniciando retiro de dinero para la cuenta: {}", account);
			log.info("iaccount {}", iaccount);
			double originalBalance = account.getBalance();
			ResponseEntity<ResponseTO<Boolean>> response = Helper.doWithdraw(iaccount, account, withdraw.getAmount());
			
			if(response.getBody().getStatus() == -1) {
				log.info("Se reversará el saldo");
				reverse(withdraw, originalBalance);
			} else {
				log.info("Se guardará retiro de dinero");
				withdraw.setFolio(UUID.randomUUID().toString());
				withdraw.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				addMovement(withdraw);
				iwitdraw.save(withdraw);
			}
			return response;
		} else {
			ResponseTO<Boolean> response = new ResponseTO<Boolean>();
			response.setMessage("No se encontró la cuenta");
			response.setStatus(-1);
			response.setResponse(false);
			return new ResponseEntity<ResponseTO<Boolean>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
	
	public void addMovement(Withdraw withdraw) {
		Movement mov = new Movement();
		mov.setAccount(withdraw.getAccount());
		mov.setAmount(withdraw.getAmount());
		mov.setConcept("Retiro de dinero");
		mov.setDate(withdraw.getDate());
		mov.setType(0);
		imovement.save(mov);
	}
}
