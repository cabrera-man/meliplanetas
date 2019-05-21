package manu.cab.planetas.service.scheduleTasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import manu.cab.planetas.domain.CuerpoCeleste;
import manu.cab.planetas.domain.OrbitaPlaneta;
import manu.cab.planetas.domain.OrbitaSol;
import manu.cab.planetas.domain.Pronostico;
import manu.cab.planetas.repository.PronosticoRepository;
import manu.cab.planetas.service.GeomHelper;

@Component
public class PopulateDB {
	
	Logger logger = LoggerFactory.getLogger(PopulateDB.class);
	
	@Autowired
	private PronosticoRepository pronosticoRepository;
	
	@Autowired
	private GeomHelper geomHelper;
	
	public void populateDB() {
		CuerpoCeleste pA = new CuerpoCeleste(new OrbitaPlaneta(3, 2000));
		CuerpoCeleste pB = new CuerpoCeleste(new OrbitaPlaneta(-5, 1000));
		CuerpoCeleste pC = new CuerpoCeleste(new OrbitaPlaneta(1, 500));
		
		CuerpoCeleste sol = new CuerpoCeleste(new OrbitaSol());
		
		int countCollinear = 0;
		int countSequia = 0;
		
		int countPeriodoLluvia = 0;
		boolean flagPeriodoLluvia = false;
		Double maxPerimetro = new Double(0d);
		int diaMaxLluvia = 0;
		
		double signumPrevPlanet = -1f;
		double signumPrevSun = -1f;
		
		
		
		for(int n = -2; n < 365*10; n++) {
			String clima = "Dia común";
			
			double areaPlanet = geomHelper.area(n, pA, pB, pC);
			
			double areaSun = geomHelper.area(n, pA, pB, sol);
			
			boolean isSunInsideTriangle = geomHelper.pointInTriangle(n, pA, pB, pC, sol);
			if(isSunInsideTriangle) {
				//Nuevo periodo de lluvia
				if(flagPeriodoLluvia == false) {
					flagPeriodoLluvia = true;
					countPeriodoLluvia++;
				}
				
				//Max perimetro del triangulo
				double perimetroPlanet = geomHelper.perimeter(n, pA, pB, pC);
				if(maxPerimetro.compareTo(new Double(perimetroPlanet)) < 0 ) {
					diaMaxLluvia = n;
					maxPerimetro = new Double(perimetroPlanet);
				}
				clima = "Lluvia";
			} else {
				flagPeriodoLluvia = false;
			}
			
			//Para saber si hubo colinearidad uso el cambio de signo del area del triangulo
			//formado por los planetas. La impresición del double hace que la ecuacion
			// Area == 0 no sea confiable
			if(Math.signum(areaPlanet) != signumPrevPlanet && Math.signum(areaPlanet) != 0f) {
				signumPrevPlanet = Math.signum(areaPlanet);
				if(Math.signum(areaSun) != signumPrevSun && Math.signum(areaSun) != 0f) {
					signumPrevSun = Math.signum(areaSun);
					countSequia++;
					clima = "Sequía";
				} else {
					countCollinear++;
					clima = "Condiciones óptimas de presión y temperatura";
				}
			}
			//Cambio de signo del area del sol
			if(Math.signum(areaSun) != signumPrevSun && Math.signum(areaSun) != 0f) {
				signumPrevSun = Math.signum(areaSun);
			}
			
			Pronostico pronostico = new Pronostico();
			pronostico.setDia(n);
			pronostico.setClima(clima);
			
			pronosticoRepository.save(pronostico);
		}
		
		logger.info("Períodos de sequía: " + countSequia);
		logger.info("Períodos de lluvia: " + countPeriodoLluvia);
		logger.info("Día de mayor lluvía: " + diaMaxLluvia);
		logger.info("Períodos de cond. óptimas de presión y temperatura: " + countCollinear);
	}

}
