package br.com.edu.book.service.controllers;

import br.com.edu.book.service.entities.Book;
import br.com.edu.book.service.proxy.CambioProxy;
import br.com.edu.book.service.repositories.BookRepository;
import br.com.edu.book.service.response.Cambio;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

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
