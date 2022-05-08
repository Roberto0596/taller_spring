package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IFrecuent;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Frecuent;
import com.example.demo.services.FrecuentService;

@Service
public class FrecuentServiceImpl implements FrecuentService {
	
	private static final Logger log = LoggerFactory.getLogger(FrecuentServiceImpl.class);
	
	@Autowired
	private IFrecuent ifrecuent;

	@Override
	public ResponseEntity<ResponseTO<List<Frecuent>>> getAll(long customerId) {
		log.info("Cargando frecuentes {}", customerId);
		ResponseTO<List<Frecuent>> response = new ResponseTO<List<Frecuent>>();
		try {
			log.info("Consultando listas");
			List<Frecuent> accounts = ifrecuent.getFrecuentsByCustomerId(customerId);
			response.setStatus(0);
			
			if(accounts.isEmpty()) {
				log.info("no existen frecuentes");
				response.setMessage("No se encontraron registros");
				return new ResponseEntity<ResponseTO<List<Frecuent>>>(response, HttpStatus.NO_CONTENT);
			}
			
			log.info("Lista de frecuentes {}", accounts);
			
			response.setMessage("Frecuentes consultadas con exito");
			response.setResponse(accounts);
			return new ResponseEntity<ResponseTO<List<Frecuent>>>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.setMessage("Incidencia al consultar");
			response.setResponse(null);
			response.setStatus(-1);
			log.info("Incidencia al realizar consulta de freucentes {}", e);
			return new ResponseEntity<ResponseTO<List<Frecuent>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseTO<Frecuent>> getFrecuent(long idFrecuent) {
		log.info("Consultando frecuente {}", idFrecuent);
		ResponseTO<Frecuent> response = new ResponseTO<Frecuent>();
		try {
			Optional<Frecuent> frecuent = ifrecuent.findById(idFrecuent);
			response.setStatus(0);
			
			if(!frecuent.isPresent()) {
				log.info("no existen frecuentes");
				response.setMessage("No se encontraron registros");
				return new ResponseEntity<ResponseTO<Frecuent>>(response, HttpStatus.NO_CONTENT);
			}
						
			response.setMessage("Frecuentes consultado exitosa");
			response.setResponse(frecuent.get());
			return new ResponseEntity<ResponseTO<Frecuent>>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.setMessage("Incidencia al consultar");
			response.setResponse(null);
			response.setStatus(-1);
			log.info("Incidencia al realizar consulta de freucentes {}", e);
			return new ResponseEntity<ResponseTO<Frecuent>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseTO<Boolean>> delete(long frecuentId) {
		log.info("Borrando frecuente {}", frecuentId);
		ResponseTO<Boolean> response = new ResponseTO<Boolean>();
		try {
			ifrecuent.deleteById(frecuentId);
			response.setStatus(0);						
			response.setMessage("Frecuentes Eliminado exitosamente");
			response.setResponse(true);
			return new ResponseEntity<ResponseTO<Boolean>>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.setMessage("Incidencia al eliminar");
			response.setResponse(false);
			response.setStatus(-1);
			log.info("Incidencia al realizar elimado de freucentes {}", e);
			return new ResponseEntity<ResponseTO<Boolean>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseTO<Frecuent>> save(Frecuent frecuent) {
		log.info("Iniciando proceso de guardado :{}", frecuent.toString());
		ResponseTO<Frecuent> response = new ResponseTO<Frecuent>();
		try {	
			Frecuent mergedFrecuent = ifrecuent.save(frecuent);
			response.setStatus(0);
			response.setMessage("Cuenta guardada exitosamente");
			response.setResponse(mergedFrecuent);
			return new ResponseEntity<ResponseTO<Frecuent>>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.setStatus(-1);
			response.setMessage("Ocurrió un problema al guardar la cuenta");
			log.info("Incidencia al realizar guardado {}", e);
			return new ResponseEntity<ResponseTO<Frecuent>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
