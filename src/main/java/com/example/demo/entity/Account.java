package com.example.demo.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne()
	@JoinColumn(name = "id_customer")
	private Customer customer;
	
	@Column(name="number", unique=true)
	private String number;
	
	@Column(name="balance")
	private double balance;
	
	@OneToMany(mappedBy = "account")
    @JsonIgnore
	private List<Movement> movements;
	
	@OneToMany(mappedBy = "account")
    @JsonIgnore
	private List<Transfer> transfers;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}

	public List<Transfer> getTransfers() {
		return transfers;
	}

	public void setTransfers(List<Transfer> transfers) {
		this.transfers = transfers;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", customer=" + customer + ", number=" + number + ", balance=" + balance
				+ ", movements=" + movements + ", transfers=" + transfers + "]";
	}	
}
