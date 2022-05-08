package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IAccount;
import com.example.demo.DAO.ICustomer;
import com.example.demo.Utils.Helper;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Frecuent;
import com.example.demo.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private IAccount iaccount;
	
	@Autowired
	private ICustomer icustomer;
	
	@Override
	public ResponseEntity<ResponseTO<List<Account>>> getAccounts(String customerId) {
		log.info("Cargando cuentas y saldos {}", customerId);
		ResponseTO<List<Account>> response = new ResponseTO<List<Account>>();
		try {
			log.info("Consultando listas");
			List<Account> accounts = iaccount.getAccountsByCustomerId(Long.parseLong(customerId));
			response.setStatus(0);
			
			if(accounts.isEmpty()) {
				log.info("no existen cuentas");
				response.setMessage("No se encontraron registros");
				return new ResponseEntity<ResponseTO<List<Account>>>(response, HttpStatus.NO_CONTENT);
			}
			
			log.info("Lista de cuentas {}", accounts);
			
			response.setMessage("Cuentas consultadas con exito");
			response.setResponse(accounts);
			return new ResponseEntity<ResponseTO<List<Account>>>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.setMessage("Incidencia al consultar");
			response.setResponse(null);
			response.setStatus(-1);
			log.info("Incidencia al realizar login {}", e);
			return new ResponseEntity<ResponseTO<List<Account>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<ResponseTO<Account>> getAccount(long idAccount) {
		log.info("Consultando frecuente {}", idAccount);
		ResponseTO<Account> response = new ResponseTO<Account>();
		try {
			Optional<Account> account = iaccount.findById(idAccount);
			response.setStatus(0);
			
			if(!account.isPresent()) {
				log.info("no existen cuentas");
				response.setMessage("No se encontraron registros");
				return new ResponseEntity<ResponseTO<Account>>(response, HttpStatus.NO_CONTENT);
			}
						
			response.setMessage("Cuenta consultado exitosa");
			response.setResponse(account.get());
			return new ResponseEntity<ResponseTO<Account>>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.setMessage("Incidencia al consultar");
			response.setResponse(null);
			response.setStatus(-1);
			log.info("Incidencia al realizar consulta de freucentes {}", e);
			return new ResponseEntity<ResponseTO<Account>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<ResponseTO<Account>> save(Account account) {
		log.info("Iniciando proceso de guardado :{}", account.toString());
		ResponseTO<Account> response = new ResponseTO<Account>();
		try {	
			Account mergedAccount = iaccount.save(account);
			response.setStatus(0);
			response.setMessage("Cuenta guardada exitosamente");
			response.setResponse(mergedAccount);
			return new ResponseEntity<ResponseTO<Account>>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.setStatus(-1);
			response.setMessage("Ocurrió un problema al guardar la cuenta");
			log.info("Incidencia al realizar guardado {}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
