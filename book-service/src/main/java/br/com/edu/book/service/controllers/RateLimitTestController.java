package br.com.edu.book.service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/book-service")
public class RateLimitTestController {

	private final Logger logger = LoggerFactory.getLogger(RateLimitTestController.class);

	@GetMapping("/ratelimit")
	@RateLimiter(name = "default")
	public String test() {
		logger.info("Request for test rate limit received!");
		return "rate-limit";
	}

}
