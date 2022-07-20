package br.com.edu.cambio.service.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.edu.cambio.service.entities.Cambio;
import br.com.edu.cambio.service.exceptions.NoRecordsFoundException;
import br.com.edu.cambio.service.exceptions.UnsupportedCurrencyException;
import br.com.edu.cambio.service.repositories.CambioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cambio Service API")
@RestController
@RequestMapping("/cambio-service")
public class CambioController {

	private final Logger logger = LoggerFactory.getLogger(CambioController.class);

	private final Environment environment;

	private final CambioRepository cambioRepository;

	public CambioController(final Environment environment, final CambioRepository cambioRepository) {
		this.environment = environment;
		this.cambioRepository = cambioRepository;
	}

	@Operation(summary = "Get cambio between currencies")
	@GetMapping("/{amount}/{from}/{to}")
	public Cambio getCambio(@PathVariable("amount") final BigDecimal amount, @PathVariable("from") final String from,
			@PathVariable("to") final String to) {

		logger.info("Calling cambioController amount: %s from %s to %s.", amount, from, to);

		final var cambio = cambioRepository.findByFromAndTo(from, to);

		if (cambio == null) {
			logger.error("Unsupported currency!");
			throw new UnsupportedCurrencyException();
		}

		final var conversionFactor = cambio.getConversionFactor();

		final var convertedValue = conversionFactor.multiply(amount);

		final var port = environment.getProperty("local.server.port");
		cambio.setEnvironment(port);
		cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.HALF_UP));

		return cambio;
	}

	@GetMapping
	public List<Cambio> list() {
		final var cambios = cambioRepository.findAll();

		if (!cambios.isEmpty()) {
			throw new NoRecordsFoundException();
		}

		return List.copyOf(cambios);
	}

}
