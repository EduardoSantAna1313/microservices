package br.com.edu.book.service.controllers;

import br.com.edu.book.service.entities.Book;
import br.com.edu.book.service.proxy.CambioProxy;
import br.com.edu.book.service.repositories.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("/book-service")
public class BookController {

    private final Environment environment;

    private final BookRepository bookRepository;

    private final CambioProxy cambioProxy;

    public BookController(Environment environment, BookRepository bookRepository, CambioProxy cambioProxy) {
        this.environment = environment;
        this.bookRepository = bookRepository;
        this.cambioProxy = cambioProxy;
    }

    @Operation(summary = "Find a specific book by ID")
    @GetMapping("/{id}/{currency}")
    public Book getBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {

        final var book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book does not exists!"));

        System.out.println("From: " + "USD");
        System.out.println("To: " + currency);

        final var cambio =  cambioProxy.getCambio(book.getPrice(), "USD", currency);

        System.out.println("Câmbio: " + cambio);

        book.setEnvironment("Book-service with feign: " + environment.getProperty("local.server.port")
                + "\ncambio: " + cambio.getEnvironment());
        if (cambio.getConvertedValue() != null) {
            book.setPrice(BigDecimal.valueOf(cambio.getConvertedValue()));
        }
        return book;
    }
}
