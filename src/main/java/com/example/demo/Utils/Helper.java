package com.example.demo.Utils;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.DAO.IAccount;
import com.example.demo.beans.OperationTO;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.*;

@Component
public class Helper {

	private static final Logger log = LoggerFactory.getLogger(Helper.class);

	public static boolean validatePassword(Customer customer, String password) {
		return (customer.getPassword().equals(password)) ? true : false;
	}

	public static ResponseEntity<ResponseTO<Boolean>> doWithdraw(IAccount iaccount, Account accountObj, double amount) {
		ResponseTO<Boolean> response = new ResponseTO<Boolean>();
		try {
			Optional<Account> account = iaccount.findById(accountObj.getId());
			
			if(!account.isPresent()) {
				log.info("No se encontró la cuenta a la que se hace referencia");
				response.setMessage("No se encontró la cuenta a la que se hace referencia");
				response.setStatus(-1);
				response.setResponse(false);
				return new ResponseEntity<ResponseTO<Boolean>>(response, HttpStatus.NO_CONTENT);
			}
			
			Account aux = account.get();
			double balance = aux.getBalance();
			double resultNewBalance = balance - amount;
			
			if(amount > balance && resultNewBalance < 0) {
				log.info("El saldo de salida es menor al balance de la cuenta");
				response.setMessage("El saldo de salida es menor al balance de la cuenta");
				response.setStatus(-1);
				response.setResponse(false);
				return new ResponseEntity<ResponseTO<Boolean>>(response, HttpStatus.OK);
			}	
			
			aux.setBalance(resultNewBalance);
			iaccount.save(aux);
			response.setMessage("Operacion realizado con exito");
			response.setStatus(0);
			response.setResponse(true);
			log.info("Retiro realizado correctamente");
			return new ResponseEntity<ResponseTO<Boolean>>(response, HttpStatus.OK);
		} catch(Exception e) {
			log.info("Incidencia al realizar operacion {}", e.getMessage());
			response.setMessage("Ocurrio un error al realizar el retiro");
			response.setStatus(-1);
			response.setResponse(false);
			return new ResponseEntity<ResponseTO<Boolean>>(response, HttpStatus.OK);
		}
	}

	public static Transfer buildTransferObject(OperationTO operation, Account account, Frecuent frecuent) {
		Transfer transfer = new Transfer();
		transfer.setAccount(account);
		transfer.setFrecuent(frecuent);
		transfer.setAmount(operation.getAmount());
		return transfer;
	}

	public static Withdraw buildWithdrawObject(OperationTO operation, Account account) {
		Withdraw obj = new Withdraw();
		obj.setAccount(account);
		obj.setAmount(operation.getAmount());
		return obj;
	}
}
