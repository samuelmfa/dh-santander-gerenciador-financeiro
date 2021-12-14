package com.santander.gf.exceptions;

public class LimiteDespesaError extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public LimiteDespesaError(String msg) {
		super(msg);
	}

	public LimiteDespesaError(String msg, Throwable cause) {
		super(msg, cause);
	}

}
