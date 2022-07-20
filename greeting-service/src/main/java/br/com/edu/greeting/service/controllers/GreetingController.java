package br.com.edu.greeting.service.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.edu.greeting.service.configuration.GreetingConfiguration;
import br.com.edu.greeting.service.services.Greeting;

@RestController
public class GreetingController {

	private static final String TEMPLATE = "%s, %s!";

	private final AtomicLong counter = new AtomicLong();

	private final GreetingConfiguration configuration;

	public GreetingController(final GreetingConfiguration configuration) {
		this.configuration = configuration;
	}

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "") String name) {

		if (name.isEmpty()) {
			name = configuration.getDefaultValue();
		}

		return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, configuration.getGreeting(), name));
	}

}
