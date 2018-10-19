package com.bkda.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import com.bkda.dto.ErrorContent;
import com.bkda.dto.response.ContentResponse;

@ControllerAdvice
@RestController
public class BKDAResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private Environment env;
	
	@ExceptionHandler(BKDAServiceException.class)
	public final ResponseEntity<ContentResponse<String>> onServiceException(BKDAServiceException ex, WebRequest request) {
		ContentResponse<String> response = new ContentResponse<>();
		response.setContent("ERROR");
		response.setMessage(ex.getMessage());
		response.setInternalCode(ex.getErrorCode());
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		if( ex.getErrorCode() >= 1001 && ex.getErrorCode() <= 1030 ) {
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<>(response, status);
	}
	
	@ExceptionHandler(MultiErrorsException.class)
	public final ResponseEntity<ContentResponse<List<ErrorContent>>> onMultiErrorsException(MultiErrorsException ex, WebRequest request) {
		ContentResponse<List<ErrorContent>> response = new ContentResponse<>();
		response.setContent(ex.getErrors());
		response.setMessage(ex.getMessage());
		// TODO: define multi error Code to use here!
		response.setInternalCode(1000);
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
//		if( ex.getErrorCode() >= 1001 && ex.getErrorCode() <= 1030 ) {
//			status = HttpStatus.BAD_REQUEST;
//		}
		
		return new ResponseEntity<>(response, status);
	}
}
