package manu.cab.planetas.web.rest;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import manu.cab.planetas.domain.Pronostico;
import manu.cab.planetas.repository.PronosticoRepository;

@RestController
public class ClimaResource {
	
	@Inject
	private PronosticoRepository pronosticoRepository;
	
	@RequestMapping(path = "/clima", method = RequestMethod.GET)
	public String clima(@RequestParam int dia) {
		List<Pronostico> pronosticos = pronosticoRepository.findByDia(dia);
		return pronosticos.get(0).getClima();
	}
}
