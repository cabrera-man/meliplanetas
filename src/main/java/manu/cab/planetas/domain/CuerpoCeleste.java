package manu.cab.planetas.domain;

import java.awt.geom.Point2D;

public class CuerpoCeleste {
	
	private Orbita orbita;
	
	public CuerpoCeleste (Orbita orbita) {
		this.orbita = orbita;
	}

	public Point2D getPoint2D(int dia) {
		return orbita.getPoint2D(dia);
	}
	
	
}
