package manu.cab.planetas.service.util;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import org.junit.Test;

import manu.cab.planetas.domain.CuerpoCeleste;
import manu.cab.planetas.domain.Orbita;

public class GeomHelperTest {
	
	GeomHelper geomHelper = new GeomHelper();
	
	private class PuntoEstatico implements Orbita{
		
		private Point2D point;
		
		public PuntoEstatico(int x, int y) {
			this.point = new Double(x, y);
		}

		@Override
		public Point2D getPoint2D(int dia) {
			return this.point;
		}
	}
	
	CuerpoCeleste punto1 = new CuerpoCeleste(new PuntoEstatico(-1, 0));
	CuerpoCeleste punto2 = new CuerpoCeleste(new PuntoEstatico(0, 2));
	CuerpoCeleste punto3 = new CuerpoCeleste(new PuntoEstatico(1, 0));
	
	CuerpoCeleste puntoDentroTriangulo = new CuerpoCeleste(new PuntoEstatico(0, 1));
	
	CuerpoCeleste puntoFueraTriangulo = new CuerpoCeleste(new PuntoEstatico(-50, 1));

	@Test
	public void areaOrientedClockwise() {
		double area = geomHelper.area(1, punto1, punto2, punto3);
		assertEquals(-2d, area, java.lang.Double.valueOf("1E-5"));
	}
	
	@Test
	public void areaOrientedCounterClockwise() {
		double area = geomHelper.area(1, punto3, punto2, punto1);
		assertEquals(2d, area, java.lang.Double.valueOf("1E-5"));
	}
	
	@Test
	public void perimeter() {
		double perimeter  = geomHelper.perimeter(1, punto1, punto2, punto3);
		
		assertEquals(6.47213595d, perimeter, java.lang.Double.valueOf("1E-5"));
	}
	
	@Test
	public void pointInTriangle() {
		assertTrue(geomHelper.pointInTriangle(2, punto1, punto2, punto3, puntoDentroTriangulo));
	}
	
	@Test
	public void pointOutsiedeTriangle() {
		assertFalse(geomHelper.pointInTriangle(2, punto1, punto2, punto3, puntoFueraTriangulo));
	}

}
