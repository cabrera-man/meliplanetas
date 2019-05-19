package manu.cab.planetas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import manu.cab.planetas.domain.Pronostico;

public interface PronosticoRepository extends JpaRepository<Pronostico, Long> {
	
	List<Pronostico> findByDia(int dia);

}
