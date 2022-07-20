package br.com.edu.book.service.proxy;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.edu.book.service.response.Cambio;

@FeignClient(name = "cambio-service")
public interface CambioProxy {

	@GetMapping("/cambio-service/{amount}/{from}/{to}")
	public Cambio getCambio(@PathVariable("amount") final BigDecimal amount, @PathVariable("from") final String from,
			@PathVariable("to") final String to);

}
