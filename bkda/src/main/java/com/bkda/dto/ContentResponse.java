package com.bkda.dto;

public class ContentResponse<T> extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8815091521967543135L;
	
	private T content;
	
	public ContentResponse() { }
	
	public ContentResponse(T content) {
		this.content = content;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

}
