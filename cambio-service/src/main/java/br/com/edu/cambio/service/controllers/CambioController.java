package br.com.edu.cambio.service.controllers;

import br.com.edu.cambio.service.entities.Cambio;
import br.com.edu.cambio.service.repositories.CambioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Tag(name = "Cambio Service API")
@RestController
@RequestMapping("/cambio-service")
public class CambioController {

    private final Environment environment;

    private final CambioRepository cambioRepository;

    public CambioController(Environment environment, CambioRepository cambioRepository) {
        this.environment = environment;
        this.cambioRepository = cambioRepository;
    }

    @Operation(summary = "Get cambio between currencies")
    @GetMapping("/{amount}/{from}/{to}")
    public Cambio getCambio(@PathVariable("amount") BigDecimal amount,
                            @PathVariable("from") String from,
                            @PathVariable("to") String to) {

        final var cambio = cambioRepository.findByFromAndTo(from, to);

        if (cambio == null) {
            throw new RuntimeException("Unsupported currency!");
        }

        var conversionFactor = cambio.getConversionFactor();
        var convertedValue = conversionFactor.multiply(amount);

        final var port = environment.getProperty("local.server.port");
        cambio.setEnvironment(port);
        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.HALF_UP));

        System.out.println(cambio);

        return cambio;
    }
}
