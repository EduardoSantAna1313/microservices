package br.com.edu.book.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BookServiceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

}
