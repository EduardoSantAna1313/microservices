package br.com.edu.cambio.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.edu.cambio.service.entities.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long> {

	public Cambio findByFromAndTo(final String from, final String to);

}
