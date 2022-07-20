package br.com.edu.book.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.edu.book.service.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
