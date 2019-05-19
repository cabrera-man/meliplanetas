package manu.cab.planetas.service;

import java.awt.geom.Point2D;

import org.springframework.stereotype.Service;

import manu.cab.planetas.domain.Planeta;

@Service
public class GeomHelper {
	
	public double area(int dia, Planeta planetaA, Planeta planetaB, Planeta planetaC) {
		double part1 = planetaA.getPoint2D(dia).getX()*(planetaB.getPoint2D(dia).getY() - planetaC.getPoint2D(dia).getY());
		double part2 = planetaB.getPoint2D(dia).getX()*(planetaA.getPoint2D(dia).getY() - planetaC.getPoint2D(dia).getY());
		double part3 = planetaC.getPoint2D(dia).getX()*(planetaA.getPoint2D(dia).getY() - planetaB.getPoint2D(dia).getY());
		
		double result = (part1 + part2 + part3)/2;
		
		return result;
	}
	
	public double perimeter(int dia, Planeta planetaA, Planeta planetaB, Planeta planetaC) {
		
		double distPAPB = Math.sqrt(Math.pow(planetaB.getPoint2D(dia).getX() - planetaA.getPoint2D(dia).getX(), 2d) + Math.pow(planetaB.getPoint2D(dia).getY() - planetaA.getPoint2D(dia).getY(), 2d));
		double distPBPC = Math.sqrt(Math.pow(planetaC.getPoint2D(dia).getX() - planetaB.getPoint2D(dia).getX(), 2d) + Math.pow(planetaC.getPoint2D(dia).getY() - planetaB.getPoint2D(dia).getY(), 2d));
		double distPCPA = Math.sqrt(Math.pow(planetaA.getPoint2D(dia).getX() - planetaC.getPoint2D(dia).getX(), 2d) + Math.pow(planetaA.getPoint2D(dia).getY() - planetaC.getPoint2D(dia).getY(), 2d));
		
		return distPAPB + distPBPC + distPCPA;
	}
	
	public boolean pointInTriangle(int dia, Planeta planetaA, Planeta planetaB, Planeta planetaC, Planeta sol)
	{
		//Honestidad 100%
		//https://www.youtube.com/watch?v=HYAgJN3x4GA
		//https://github.com/SebLague/Gamedev-Maths/blob/master/PointInTriangle.cs
		Point2D posPA = planetaA.getPoint2D(dia);
		Point2D posPB = planetaB.getPoint2D(dia);
		Point2D posPC = planetaC.getPoint2D(dia);
		Point2D posSun = sol.getPoint2D(dia);
		
		double s1 = posPC.getY() - posPA.getY();
		double s2 = posPC.getX() - posPA.getX();
		double s3 = posPB.getY() - posPA.getY();
		double s4 = posSun.getY() - posPA.getY();
		
		double w1 = (posPA.getX() * s1 + s4 * s2 - posSun.getX() * s1) / (s3 * s2 - (posPB.getX()-posPA.getX()) * s1);
		
		double w2 = (s4- w1 * s3) / s1;
		return w1 >= 0 && w2 >= 0 && (w1 + w2) <= 1;
	}

}
