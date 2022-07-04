package br.com.edu.book.service.controllers;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/book-service")
public class TestRetry {

    private Logger logger = LoggerFactory.getLogger(TestRetry.class);

    @GetMapping("/retry")
    @Retry(name = "default", fallbackMethod = "fallback")
    public String test() {
        logger.info("Request for test received!");

        var result =  new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);

        return "testing";
    }

    public String fallback(Exception error) {
        return "fallbackMethod!!!!";
    }


}
