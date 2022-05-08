package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.ICustomer;
import com.example.demo.Utils.Helper;
import com.example.demo.beans.LoginRequestTO;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Customer;
import com.example.demo.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private ICustomer icustomer;
	
	@Override
	public ResponseEntity<ResponseTO<Customer>> login(LoginRequestTO loginTo) {
		log.info("Iniciando login usuario :{}", loginTo.getUsername());
		ResponseTO<Customer> response = new ResponseTO<Customer>();
		try {
			log.info("Realizando proceso de login");
			List<Customer> login = icustomer.doLogin(loginTo.getUsername());
			response.setStatus(0);
			
			if(login.isEmpty()) {
				log.info("Login vacio");
				response.setMessage("No se encontraron registros");
				return new ResponseEntity<ResponseTO<Customer>>(response, HttpStatus.NO_CONTENT);
			}
			
			if(Helper.validatePassword(login.get(0), loginTo.getPassword())) {
				response.setResponse(login.get(0));
				response.setMessage("Login exitoso");
				log.info("Login exitoso");
			} else {
				response.setMessage("Contraseña incorrecta");
				log.info("Contraseña incorrecta");
			}
			
			return new ResponseEntity<ResponseTO<Customer>>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.setMessage("Incidencia al consultar");
			response.setResponse(null);
			response.setStatus(-1);
			log.info("Incidencia al realizar login {}", e);
			return new ResponseEntity<ResponseTO<Customer>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<ResponseTO<Customer>> save(Customer customer) {
		log.info("Iniciando proceso de guardado :{}", customer.toString());
		ResponseTO<Customer> response = new ResponseTO<Customer>();
		try {	
			Customer mergedCustomer = icustomer.save(customer);
			response.setStatus(0);
			response.setMessage("Cliente guardado exitosamente");
			response.setResponse(mergedCustomer);
			return new ResponseEntity<ResponseTO<Customer>>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.setStatus(-1);
			response.setMessage("Ocurrió un problema al guardar el cliente");
			log.info("Incidencia al realizar guardado {}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
