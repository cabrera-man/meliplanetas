package manu.cab.planetas.domain;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public class Planeta {
	
	private int gradosPorDia;
	
	private int distancia;
	
	public Planeta (int gradosPorDia, int distancia) {
		this.gradosPorDia = gradosPorDia;
		this.distancia = distancia;
	}
	
	public Planeta() {
		// TODO Auto-generated constructor stub
	}

	public Point2D getPoint2D(int dia) {
		float x  = (float)(distancia * Math.sin(Math.toRadians(gradosPorDia * dia)));
		float y =  (float)(distancia * Math.cos(Math.toRadians(gradosPorDia * dia)));
		return new Float(x, y);
	}
	
	
}
