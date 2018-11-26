package com.revature.exception;

public class WithdrawalAmountExceedsBalanceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7874301954504493571L;

	public WithdrawalAmountExceedsBalanceException(String message) {
		super(message);
	}
}
