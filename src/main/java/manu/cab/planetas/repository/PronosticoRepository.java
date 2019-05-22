package manu.cab.planetas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import manu.cab.planetas.domain.dto.Pronostico;

public interface PronosticoRepository extends JpaRepository<Pronostico, Integer> {
	
	List<Pronostico> findByDia(Integer dia);

}
