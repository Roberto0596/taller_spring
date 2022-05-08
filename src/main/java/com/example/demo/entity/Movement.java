package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="movement")
public class Movement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "id_account")
	private Account account;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "type")
	private int type; //0 == salida, 1 == entrada
	
	@Column(name = "concept")
	private String concept;
	
	@Column(name="date")
	private Date date;
	
	
}
