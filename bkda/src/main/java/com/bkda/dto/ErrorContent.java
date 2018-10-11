package com.bkda.dto;

import java.io.Serializable;

public class ErrorContent implements Serializable {
	
	private static final long serialVersionUID = 5513968951524058871L;
	
	private int code;
	private String message;
	
	public ErrorContent(int code, String msg) {
		this.code = code;
		this.message = msg;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
