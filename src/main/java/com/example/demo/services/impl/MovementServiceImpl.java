package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IMovement;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Movement;
import com.example.demo.services.MovementService;

@Service
public class MovementServiceImpl implements MovementService {
	private static final Logger log = LoggerFactory.getLogger(FrecuentServiceImpl.class);
	
	@Autowired
	private IMovement imovement;

	@Override
	public ResponseEntity<ResponseTO<List<Movement>>> getMovements(long accountId) {
		log.info("Cargando movimientos por cuenta {}", accountId);
		ResponseTO<List<Movement>> response = new ResponseTO<List<Movement>>();
		try {
			log.info("Consultando listas");
			List<Movement> accounts = imovement.getMovementsByAccountId(accountId);
			response.setStatus(0);
			
			if(accounts.isEmpty()) {
				log.info("no existen movimientos");
				response.setMessage("No se encontraron registros");
				return new ResponseEntity<ResponseTO<List<Movement>>>(response, HttpStatus.NO_CONTENT);
			}
			
			log.info("Lista de movimientos {}", accounts);
			
			response.setMessage("Movimientos consultadas con exito");
			response.setResponse(accounts);
			return new ResponseEntity<ResponseTO<List<Movement>>>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.setMessage("Incidencia al consultar");
			response.setResponse(null);
			response.setStatus(-1);
			log.info("Incidencia al realizar consulta de movimientos {}", e);
			return new ResponseEntity<ResponseTO<List<Movement>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseTO<Movement>> getMovementById(long movementId) {
		log.info("Consultando detalle movimiento {}", movementId);
		ResponseTO<Movement> response = new ResponseTO<Movement>();
		try {
			Optional<Movement> movement = imovement.findById(movementId);
			response.setStatus(0);
			
			if(!movement.isPresent()) {
				log.info("no existen el movimiento");
				response.setMessage("No se encontraron registros");
				return new ResponseEntity<ResponseTO<Movement>>(response, HttpStatus.NO_CONTENT);
			}
						
			response.setMessage("Movimiento consultado exitosamente");
			response.setResponse(movement.get());
			return new ResponseEntity<ResponseTO<Movement>>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.setMessage("Incidencia al consultar");
			response.setResponse(null);
			response.setStatus(-1);
			log.info("Incidencia al realizar consulta de freucentes {}", e);
			return new ResponseEntity<ResponseTO<Movement>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void save(Movement movement) {
		log.info("Iniciando proceso de guardado :{}", movement.toString());
		ResponseTO<Movement> response = new ResponseTO<Movement>();
		try {	
			Movement mergedMovement = imovement.save(movement);
			response.setStatus(0);
			response.setMessage("Cuenta guardada exitosamente");
			response.setResponse(mergedMovement);
		} catch(Exception e) {
			response.setStatus(-1);
			response.setMessage("Ocurrió un problema al guardar el movimiento");
			log.info("Incidencia al realizar guardado {}", e);
		}		
	}
}
