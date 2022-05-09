package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.*;

import org.springframework.http.ResponseEntity;

import com.example.demo.beans.ResponseTO;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="transfer") 
public class Transfer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne()
	@JoinColumn(name = "id_frecuent", nullable = true)
	private Frecuent frecuent;
	
	@ManyToOne()
	@JoinColumn(name = "id_account")
	private Account account;
	
	@Column(unique=true, nullable=false)
	private String folio;
	
	@Column
	private double amount;
	
	@Column
	private Date date;

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Frecuent getFrecuent() {
		return frecuent;
	}

	public void setFrecuent(Frecuent frecuent) {
		this.frecuent = frecuent;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}	
}
