package br.com.edu.cambio.service.repositories;

import br.com.edu.cambio.service.entities.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CambioRepository extends JpaRepository<Cambio, Long> {

    Cambio findByFromAndTo(String from, String to);

}
