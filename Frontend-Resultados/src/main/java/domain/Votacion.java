package domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class Votacion implements Serializable {
	/**
* 
*/
	private static final long serialVersionUID = 1L;
	Integer id;
	String nombre;
	List<String> opciones;
	List<Voto> votos;
	Date createdDate;
	public Votacion() {
		votos = new ArrayList<Voto>();
	}

	public List<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

	public List<Voto> getVotos() {
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	 @JsonSerialize(using=DateSerializer.class)
	    public Date getCreatedDate() {
	        return createdDate;
	    }
	    public void setCreatedDate(Date createdDate) {
	        this.createdDate = createdDate;
	    }

}
