package com.ventura.androidutils.exception;

public class LazyInternetConnectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LazyInternetConnectionException() {
		super();
	}

	public LazyInternetConnectionException(String message) {
		super(message);
	}

	public LazyInternetConnectionException(Throwable innerException) {
		super(innerException);
	}

	public LazyInternetConnectionException(String message, Throwable innerException) {
		super(message, innerException);
	}
}
