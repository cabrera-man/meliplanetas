package manu.cab.planetas.service;

import org.springframework.stereotype.Service;

import manu.cab.planetas.domain.Planeta;

@Service
public class AreaCalculator {
	
	public double area(int dia, Planeta planetaA, Planeta planetaB, Planeta planetaC) {
		double part1 = planetaA.getPoint2D(dia).getX()*(planetaB.getPoint2D(dia).getY() - planetaC.getPoint2D(dia).getY());
		double part2 = planetaB.getPoint2D(dia).getX()*(planetaA.getPoint2D(dia).getY() - planetaC.getPoint2D(dia).getY());
		double part3 = planetaC.getPoint2D(dia).getX()*(planetaA.getPoint2D(dia).getY() - planetaB.getPoint2D(dia).getY());
		
		double result = (part1 + part2 + part3)/2;
		
		return result;
	}
	
	public double perimetro(int dia, Planeta planetaA, Planeta planetaB, Planeta planetaC) {
		
		double distPAPB = Math.sqrt(Math.pow(planetaB.getPoint2D(dia).getX() - planetaA.getPoint2D(dia).getX(), 2d) + Math.pow(planetaB.getPoint2D(dia).getY() - planetaA.getPoint2D(dia).getY(), 2d));
		double distPBPC = Math.sqrt(Math.pow(planetaC.getPoint2D(dia).getX() - planetaB.getPoint2D(dia).getX(), 2d) + Math.pow(planetaC.getPoint2D(dia).getY() - planetaB.getPoint2D(dia).getY(), 2d));
		double distPCPA = Math.sqrt(Math.pow(planetaA.getPoint2D(dia).getX() - planetaC.getPoint2D(dia).getX(), 2d) + Math.pow(planetaA.getPoint2D(dia).getY() - planetaC.getPoint2D(dia).getY(), 2d));
		
		return distPAPB + distPBPC + distPCPA;
	}

}
