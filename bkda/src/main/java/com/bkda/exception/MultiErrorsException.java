package com.bkda.exception;

import java.util.List;

import com.bkda.dto.ErrorContent;

public class MultiErrorsException extends RuntimeException {

	private static final long serialVersionUID = -6812905552178972433L;
	private List<ErrorContent> errors;
	
	public MultiErrorsException(List<ErrorContent> errors, String message) {
		super(message);
		this.errors = errors;
	}

	public List<ErrorContent> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorContent>  errors) {
		this.errors = errors;
	}
}
