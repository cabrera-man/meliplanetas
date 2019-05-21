package manu.cab.planetas.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pronostico {

	@Id
	int dia;
	
	String clima;

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

}
