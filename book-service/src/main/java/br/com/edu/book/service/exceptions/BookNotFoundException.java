package br.com.edu.book.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Book does not exists!")
public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookNotFoundException() {
		super("Book does not exists!");
	}

}
