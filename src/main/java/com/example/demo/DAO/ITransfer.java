package com.example.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Transfer;

public interface ITransfer extends JpaRepository<Transfer, Long> {

}
