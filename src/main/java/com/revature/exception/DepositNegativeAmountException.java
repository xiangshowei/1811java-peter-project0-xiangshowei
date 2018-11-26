package com.revature.exception;

public class DepositNegativeAmountException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5867308396203364136L;

	public DepositNegativeAmountException(String message) {
		super(message);
	}

}
