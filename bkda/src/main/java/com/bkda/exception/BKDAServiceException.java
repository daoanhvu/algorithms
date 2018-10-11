package com.bkda.exception;

public class BKDAServiceException extends RuntimeException {

	private static final long serialVersionUID = -6812905552178972433L;
	private int errorCode;
	
	public BKDAServiceException(int code, String message) {
		super(message);
		this.errorCode = code;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
