package org.jsp.exception;

public class InvalidCredentialsException extends RuntimeException {
	@Override
	public String getMessage() {
		return " Your given wrong details pls check!";
	}
}
