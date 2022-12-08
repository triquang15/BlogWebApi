package com.devteam.exception;

public class BlogApiException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String message;

    public BlogApiException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
