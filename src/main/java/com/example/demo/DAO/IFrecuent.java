package com.example.demo.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Account;
import com.example.demo.entity.Frecuent;

public interface IFrecuent extends JpaRepository<Frecuent, Long> {
	@Query(value = "SELECT a.* FROM frecuent a WHERE a.id_customer = ?1", nativeQuery = true)
	public List<Frecuent> getFrecuentsByCustomerId(long customer);
}
