package br.com.edu.book.service.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.edu.book.service.entities.Book;
import br.com.edu.book.service.exceptions.BookNotFoundException;
import br.com.edu.book.service.proxy.CambioProxy;
import br.com.edu.book.service.repositories.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("/book-service")
public class BookController {

	private final Logger logger = LoggerFactory.getLogger(BookController.class);

	private final Environment environment;

	private final BookRepository bookRepository;

	private final CambioProxy cambioProxy;

	public BookController(final Environment environment, final BookRepository bookRepository,
			final CambioProxy cambioProxy) {
		this.environment = environment;
		this.bookRepository = bookRepository;
		this.cambioProxy = cambioProxy;
	}

	@Operation(summary = "Find a specific book by ID")
	@GetMapping("/{id}/{currency}")
	public Book getBook(@PathVariable("id") final Long id, @PathVariable("currency") final String currency) {

		final var book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);

		logger.info("Chamando bookController: {0} currency {1}.", id, currency);

		final var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);

		book.setEnvironment("Book-service with feign: " + environment.getProperty("local.server.port") + "\ncambio: "
				+ cambio.getEnvironment());

		if (cambio.getConvertedValue() != null) {
			book.setPrice(BigDecimal.valueOf(cambio.getConvertedValue()));
		}
		return book;
	}

	@GetMapping
	@Operation(summary = "List books")
	public List<Book> list() {
		return List.copyOf(bookRepository.findAll());
	}

}
