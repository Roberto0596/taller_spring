package com.example.demo.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entity.Movement;

public interface IMovement extends JpaRepository<Movement, Long> {
	@Query(value = "SELECT a.* FROM movement a WHERE a.id_account = ?1", nativeQuery = true)
	public List<Movement> getMovementsByAccountId(long accountId);
}
