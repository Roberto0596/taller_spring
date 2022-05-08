package com.example.demo.beans;

import java.io.Serializable;

public class ResponseTO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int status;
	
	private String message;
	
	private T response;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
