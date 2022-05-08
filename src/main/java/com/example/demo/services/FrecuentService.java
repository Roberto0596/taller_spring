package com.example.demo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.beans.ResponseTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Frecuent;

public interface FrecuentService {
	ResponseEntity<ResponseTO<List<Frecuent>>> getAll(long frecuentId);
	ResponseEntity<ResponseTO<Frecuent>> getFrecuent(long frecuentId);
	ResponseEntity<ResponseTO<Boolean>> delete(long frecuentId);
	ResponseEntity<ResponseTO<Frecuent>> save(Frecuent customer);
}
