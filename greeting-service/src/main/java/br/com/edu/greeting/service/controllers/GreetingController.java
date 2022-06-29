package br.com.edu.greeting.service.controllers;

import br.com.edu.greeting.service.configuration.GreetingConfiguration;
import br.com.edu.greeting.service.services.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "%s, %s!";
    private final AtomicLong counter = new AtomicLong();

    private final GreetingConfiguration configuration;

    public GreetingController(GreetingConfiguration configuration) {
        this.configuration = configuration;
    }

    @RequestMapping("/greeting")
    public Greeting greeting(
            @RequestParam(value="name",
                    defaultValue = "") String name) {

        if (name.isEmpty()) name = configuration.getDefaultValue();

        return new Greeting(
                counter.incrementAndGet(),
                String.format(template, configuration.getGreeting(), name)
        );
    }

}
