package br.com.tecnoset.register.exceptions;

public class InvalidQuantityException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	public InvalidQuantityException(String msg) {
		super(msg);
	}

}
