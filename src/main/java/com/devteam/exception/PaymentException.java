package com.devteam.exception;

public class PaymentException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentException(String message){
        super(message);
    }
}
