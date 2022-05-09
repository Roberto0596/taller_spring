package com.example.demo.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="frecuent")
public class Frecuent {	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne()
	@JoinColumn(name = "id_customer")
	private Customer customer;
	
	@Column
	private String name;
	@Column
	private String lastname;
	@Column
	private String phone;
	@Column
	private String banck;
	@Column 
	private String number_card;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBanck() {
		return banck;
	}
	public void setBanck(String banck) {
		this.banck = banck;
	}
	public String getNumber_card() {
		return number_card;
	}
	public void setNumber_card(String number_card) {
		this.number_card = number_card;
	}
}
