package manu.cab.planetas.domain;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public class OrbitaPlaneta implements Orbita {
	
	private int gradosPorDia;
	
	private int distancia;
	
	public OrbitaPlaneta(int gradosPorDia, int distancia) {
		this.gradosPorDia = gradosPorDia;
		this.distancia = distancia;
	}

	@Override
	public Point2D getPoint2D(int dia) {
		float x  = (float)(distancia * Math.sin(Math.toRadians(gradosPorDia * dia)));
		float y =  (float)(distancia * Math.cos(Math.toRadians(gradosPorDia * dia)));
		return new Float(x, y);
	}

}
