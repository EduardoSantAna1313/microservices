package br.com.edu.cambio.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No records found")
public class NoRecordsFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoRecordsFoundException() {
		super("No records found");
	}

}
