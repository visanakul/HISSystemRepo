package com.ssa.state.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssa.state.service.AccountServiceImpl;

public class COTriggerException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public COTriggerException(String errMsg) {
		super(errMsg);
	}
}
