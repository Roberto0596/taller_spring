package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Movement;
import com.example.demo.services.MovementService;

@RestController
@CrossOrigin(value="*")
@RequestMapping("/movement")
public class MovementController {
	
	@Autowired
	private MovementService service;

	@GetMapping("/{accountId}")
	public ResponseEntity<ResponseTO<List<Movement>>> getMovements(@PathVariable(value="accountId") long accountId) {
		return service.getMovements(accountId);
	}
	
	@GetMapping("/get/{movementId}")
	public ResponseEntity<ResponseTO<Movement>> getMovementById(long movementId) {
		return service.getMovementById(movementId);
	}
}
