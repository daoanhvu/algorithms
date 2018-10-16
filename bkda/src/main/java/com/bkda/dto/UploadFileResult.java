package com.bkda.dto;

import java.io.Serializable;
import java.util.List;

public class UploadFileResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6962010411165738354L;
	
	private int numOfSuccess;
	private int numOfError;
	private List<ErrorContent> errors;
	public int getNumOfSuccess() {
		return numOfSuccess;
	}
	public void setNumOfSuccess(int numOfSuccess) {
		this.numOfSuccess = numOfSuccess;
	}
	public int getNumOfError() {
		return numOfError;
	}
	public void setNumOfError(int numOfError) {
		this.numOfError = numOfError;
	}
	public List<ErrorContent> getErrors() {
		return errors;
	}
	public void setErrors(List<ErrorContent> errors) {
		this.errors = errors;
	}
	
	
}
