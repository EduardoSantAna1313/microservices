package br.com.edu.cambio.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Unsupported currency!")
public class UnsupportedCurrencyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnsupportedCurrencyException() {
		super("Unsupported currency!");
	}

}
