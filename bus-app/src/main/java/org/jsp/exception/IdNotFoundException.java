package org.jsp.exception;

public class IdNotFoundException extends RuntimeException {
	@Override
	public String getMessage() {
		return "id not available";
	}
}
