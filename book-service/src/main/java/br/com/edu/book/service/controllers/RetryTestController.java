package br.com.edu.book.service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/book-service")
public class RetryTestController {

	private final Logger logger = LoggerFactory.getLogger(RetryTestController.class);

	@GetMapping("/retry")
	@Retry(name = "default", fallbackMethod = "fallback")
	public String test() {
		logger.info("Request for test received!");

		final var result = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);

		logger.info(result.toString());

		return "testing";
	}

	public String fallback(final Exception error) {

		logger.error(error.getMessage());

		return "fallbackMethod!!!!";
	}

}
