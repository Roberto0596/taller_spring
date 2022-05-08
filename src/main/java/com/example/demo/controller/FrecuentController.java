package com.example.demo.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Frecuent;
import com.example.demo.services.FrecuentService;

@RestController
@CrossOrigin(value="*")
@RequestMapping("/frecuent")
public class FrecuentController {
	
	@Autowired
	private FrecuentService frecuentService;
	
	@GetMapping("/getFrecuents/{idCustomer}")
	public ResponseEntity<ResponseTO<List<Frecuent>>> getAll(@PathVariable(value="idCustomer") long idCustomer) {
		return frecuentService.getAll(idCustomer);
	}
	
	@GetMapping("/get/{idFrecuent}")
	public ResponseEntity<ResponseTO<Frecuent>> getFrecuent(@PathVariable(value="idFrecuent") long idFrecuent) {
		return frecuentService.getFrecuent(idFrecuent);
	}
	
	@GetMapping("/delete/{idFrecuent}")
	public ResponseEntity<ResponseTO<Boolean>> delete(@PathVariable(value="idFrecuent") long idFrecuent) {
		return frecuentService.delete(idFrecuent);
	}
	
	@PostMapping("/save")
	public ResponseEntity<ResponseTO<Frecuent>> save(@RequestBody() Frecuent frecuent) {
		return frecuentService.save(frecuent);
	}

}
