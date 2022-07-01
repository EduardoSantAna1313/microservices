package br.com.edu.book.service.controllers;

import br.com.edu.book.service.entities.Book;
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

    public BookController(Environment environment, BookRepository bookRepository) {
        this.environment = environment;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{id}/{currency}")
    public Book getBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {

        final var book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book does not exists!"));

        var params = Map.of(
                "amount", book.getPrice().toString(),
                "from", "USD",
                "to", currency);

        var cambio =    new RestTemplate().getForObject("http://localhost:8000/cambio-service/{amount}/{from}/{to}", Cambio.class,
                params);

        System.out.println("Câmbio: " + cambio);

        book.setEnvironment(environment.getProperty("local.server.port"));
        if (cambio.getConvertedValue() != null) {
            book.setPrice(BigDecimal.valueOf(cambio.getConvertedValue()));
        }
        return book;
    }
}
