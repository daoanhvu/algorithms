package com.bkda.dto;

import java.io.Serializable;

public class BaseResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6849539656908216889L;
	
	private int internalCode;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getInternalCode() {
		return internalCode;
	}

	public void setInternalCode(int internalCode) {
		this.internalCode = internalCode;
	}
	
}
