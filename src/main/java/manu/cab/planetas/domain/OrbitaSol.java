package manu.cab.planetas.domain;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public class OrbitaSol implements Orbita {

	@Override
	public Point2D getPoint2D(int dia) {
		return new Float(0, 0);
	}

}
