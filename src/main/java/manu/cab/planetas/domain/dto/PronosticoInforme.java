package manu.cab.planetas.domain.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PronosticoInforme {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String categoria;
	
	private String informe;
	
	public PronosticoInforme() {
	}
	
	public PronosticoInforme(String categoria, String informe) {
		this.categoria = categoria;
		this.informe = informe;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getInforme() {
		return informe;
	}

	public void setInforme(String informe) {
		this.informe = informe;
	}

}
