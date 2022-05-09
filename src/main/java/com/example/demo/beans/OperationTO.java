package com.example.demo.beans;

import java.io.Serializable;

public class OperationTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long accountId;
	private long frecuentId;
	private double amount;
	
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long getFrecuentId() {
		return frecuentId;
	}
	public void setFrecuentId(long frecuentId) {
		this.frecuentId = frecuentId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
