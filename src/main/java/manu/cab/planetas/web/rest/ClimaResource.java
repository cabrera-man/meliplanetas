package manu.cab.planetas.web.rest;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import manu.cab.planetas.domain.dto.Pronostico;
import manu.cab.planetas.domain.dto.PronosticoInforme;
import manu.cab.planetas.repository.PronosticoInformeRepository;
import manu.cab.planetas.repository.PronosticoRepository;

@RestController
public class ClimaResource {
	
	@Inject
	private PronosticoRepository pronosticoRepository;
	
	@Inject
	private PronosticoInformeRepository pronosticoInformeRepository;
	
	@RequestMapping(path = "/clima", method = RequestMethod.GET)
	public Pronostico clima(@RequestParam int dia) {
		List<Pronostico> pronosticos = pronosticoRepository.findByDia(dia);
		return pronosticos.get(0);
	}
	
	@RequestMapping(path = "/informe", method = RequestMethod.GET)
	public List<PronosticoInforme> informe(){
		return pronosticoInformeRepository.findAll();
	}
}
