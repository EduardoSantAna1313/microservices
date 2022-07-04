package br.com.edu.book.service.controllers;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book-service")
public class TestRateLimit {

    private Logger logger = LoggerFactory.getLogger(TestRetry.class);

    @GetMapping("/ratelimit")
    @RateLimiter(name = "default")
    public String test() {
        logger.info("Request for test rate limit received!");
        return "rate-limit";
    }

}
