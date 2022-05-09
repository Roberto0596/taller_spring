package com.example.demo.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IAccount;
import com.example.demo.DAO.IFrecuent;
import com.example.demo.DAO.IMovement;
import com.example.demo.DAO.ITransfer;
import com.example.demo.DAO.IWithDraw;
import com.example.demo.Utils.Helper;
import com.example.demo.beans.OperationTO;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Frecuent;
import com.example.demo.entity.Movement;
import com.example.demo.entity.Transfer;
import com.example.demo.entity.Withdraw;
import com.example.demo.services.TransferService;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;
@Service
public class TransferServiceImpl implements TransferService {

    private static final Logger log = LoggerFactory.getLogger(WithdrawServiceImpl.class);
	
	@Autowired
	private IAccount iaccount;
	@Autowired
	private ITransfer itransfer;
	@Autowired
	private IFrecuent ifrecuent;
	@Autowired
	private IMovement imovement;
	
	@Override
	public ResponseEntity<ResponseTO<Boolean>> doTranfer(OperationTO operation) {
		Optional<Account> oaccount = iaccount.findById(operation.getAccountId());
		Optional<Frecuent> ofrecuent = ifrecuent.findById(operation.getFrecuentId());
		
		if(oaccount.isPresent() && ofrecuent.isPresent()) {
			Account account = oaccount.get();
			Frecuent frecuent = ofrecuent.get();
			Transfer transfer = Helper.buildTransferObject(operation, account, frecuent);
			
			log.info("Iniciando retiro de dinero para la cuenta: {}", account);
			double originalBalance = transfer.getAccount().getBalance();
			ResponseEntity<ResponseTO<Boolean>> response = Helper.doWithdraw(iaccount, transfer.getAccount(), transfer.getAmount());
			
			if(response.getBody().getStatus() == -1) {
				log.info("Se reversará el saldo");
				reverse(transfer, originalBalance);
			} else {
				log.info("Se guardará trasnferencia");
				transfer.setFolio(UUID.randomUUID().toString());
				transfer.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				itransfer.save(transfer);
				addMovement(transfer);
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
	
	public void addMovement(Transfer transfer) {
		Movement mov = new Movement();
		mov.setAccount(transfer.getAccount());
		mov.setAmount(transfer.getAmount());
		mov.setConcept("Trasnferencia de dinero");
		mov.setDate(transfer.getDate());
		mov.setType(0);
		imovement.save(mov);
	}

}
