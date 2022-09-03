package com.bookservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseController {

	public BaseController() {
		super();
	}

	@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
	@ExceptionHandler(Exception.class)
	String handleAllException(Exception ex) {
	     return ex.getMessage();
	}

}