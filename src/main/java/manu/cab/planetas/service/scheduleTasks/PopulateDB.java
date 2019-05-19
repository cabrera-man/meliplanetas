package manu.cab.planetas.service.scheduleTasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import manu.cab.planetas.domain.Planeta;
import manu.cab.planetas.domain.PlanetaSol;
import manu.cab.planetas.domain.Pronostico;
import manu.cab.planetas.repository.PronosticoRepository;
import manu.cab.planetas.service.GeomHelper;

@Component
public class PopulateDB {
	@Autowired
	private PronosticoRepository pronosticoRepository;
	
	@Autowired
	private GeomHelper geomHelper;
	
	public void populateDB() {
		Planeta pA = new Planeta(3, 2000);
		Planeta pB = new Planeta(-5, 1000);
		Planeta pC = new Planeta(1, 500);
		
		Planeta sol = new PlanetaSol();
		
		int countCollinear = 0;
		int countSequia = 0;
		int countDiasLluvia = 0;
		
		double signumPrevPlanet = -1f;
		double signumPrevSun = -1f;
		
		Double maxPerimetro = new Double(0d);
		
		for(int n = 1; n < 365*10; n++) {
			String clima = "Dia común";
			
			double areaPlanet = geomHelper.area(n, pA, pB, pC);
			double areaABS = Math.abs(areaPlanet);
			
			double areaSun = geomHelper.area(n, pA, pB, sol);
			
			boolean isSunInsideTriangle = geomHelper.pointInTriangle(n, pA, pB, pC, sol);
			if(isSunInsideTriangle) {
				countDiasLluvia++;
				double perimetroPlanet = geomHelper.perimeter(n, pA, pB, pC);
				//Max perimetro del triangulo
				if(maxPerimetro.compareTo(new Double(perimetroPlanet)) < 0 ) {
					maxPerimetro = new Double(perimetroPlanet);
				}
				clima = "Lluvia";
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
					System.out.println("Cond Optimas Dia: " + n + " Area: " + areaABS);
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
	}

}
