package br.com.edu.book.service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;

@RestController
@RequestMapping("/book-service")
public class BulkheadTestController {

	private final Logger logger = LoggerFactory.getLogger(BulkheadTestController.class);

	@GetMapping("/bulkhead")
	@Bulkhead(name = "default", type = Type.SEMAPHORE)
	public String test() {
		logger.info("Request for test bulkhead received!");
		return "bulkhead";
	}

}
