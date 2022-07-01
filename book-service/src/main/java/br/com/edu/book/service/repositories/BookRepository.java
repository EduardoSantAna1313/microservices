package br.com.edu.book.service.repositories;

import br.com.edu.book.service.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {


}
