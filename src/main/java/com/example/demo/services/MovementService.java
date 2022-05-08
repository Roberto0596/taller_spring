package com.example.demo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Movement;

public interface MovementService {
	
	ResponseEntity<ResponseTO<List<Movement>>> getMovements(long accountId);
	ResponseEntity<ResponseTO<Movement>> getMovementById(long movementId);
	void save(Movement movement);
	
}
