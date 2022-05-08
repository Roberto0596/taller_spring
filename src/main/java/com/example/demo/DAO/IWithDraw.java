package com.example.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Withdraw;

public interface IWithDraw extends JpaRepository<Withdraw, Long> {

}
