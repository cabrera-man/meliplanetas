package manu.cab.planetas.service;

import static org.junit.Assert.*;

import org.junit.Test;

import manu.cab.planetas.domain.Planeta;
import manu.cab.planetas.domain.PlanetaSol;

public class AreaCalculatorTest {
	
	AreaCalculator ac = new AreaCalculator();
	
	Planeta pA = new Planeta(3, 2000);
	Planeta pB = new Planeta(-5, 1000);
	Planeta pC = new Planeta(1, 500);
	
	Planeta sol = new PlanetaSol();

	@Test
	public void test() {
		int dia = 90;
		System.out.println("Dia : " + dia);
		System.out.println("Planeta a: " + pA.getPoint2D(dia));
		System.out.println("Planeta b: " + pB.getPoint2D(dia));
		System.out.println("Planeta c: " + pC.getPoint2D(dia));
		
		fail("Not yet implemented");
	}
	
	@Test
	public void test2() {
		int countCollinear = 0;
		int countSequia = 0;
		int countDiasLluvia = 0;
		
		double signumPrevPlanet = -1f;
		double signumPrevSun = -1f;
		
		Double maxPerimetro = new Double(0d);
		
		for(int n = 1; n < 365*10; n++) {
			double areaPlanet = ac.area(n, pA, pB, pC);
			double areaABS = Math.abs(areaPlanet);
			
			double areaSun = ac.area(n, pA, pB, sol);
			
			boolean isSunInsideTriangle = ac.pointInTriangle(n, pA, pB, pC, sol);
			if(isSunInsideTriangle) {
				countDiasLluvia++;
				double perimetroPlanet = ac.perimeter(n, pA, pB, pC);
				//Max perimetro del triangulo
				if(maxPerimetro.compareTo(new Double(perimetroPlanet)) < 0 ) {
					maxPerimetro = new Double(perimetroPlanet);
				}
			}
			
			if(Math.signum(areaPlanet) != signumPrevPlanet && Math.signum(areaPlanet) != 0f) {
				signumPrevPlanet = Math.signum(areaPlanet);
				if(Math.signum(areaSun) != signumPrevSun && Math.signum(areaSun) != 0f) {
					signumPrevSun = Math.signum(areaSun);
					countSequia++;
					System.out.println("Sequia Dia: " + n + " Area: " + areaABS);
				} else {
					countCollinear++;
					System.out.println("Cond Optimas Dia: " + n + " Area: " + areaABS);
				}
			}
			//Cambio polaridad del area del sol
			if(Math.signum(areaSun) != signumPrevSun && Math.signum(areaSun) != 0f) {
				signumPrevSun = Math.signum(areaSun);
			}
			
			
		}
		System.out.println("Veces que hubo condiciones optimas: " + countCollinear);
		System.out.println("Veces que hubo sequia: " + countSequia);
		System.out.println("Dias lluvia: " + countDiasLluvia);
		System.out.println("Perimetro maximo: " + maxPerimetro);
	}

}
