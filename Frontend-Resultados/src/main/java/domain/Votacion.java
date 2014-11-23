package domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.AccessType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
@Entity
@Access(AccessType.PROPERTY)
public class Votacion extends DomainEntity implements Serializable {
	/**
* 
*/
	private static final long serialVersionUID = 1L;
	String nombre;
	List<Opcion> opciones;
	List<Voto> votos;
	Date createdDate;
	public Votacion() {
		votos = new ArrayList<Voto>();
	}
	
	@Valid
	@NotNull
	@OneToMany
	public List<Opcion> getOpciones() {
		return opciones;
	}

	
	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}

	
	@Valid
	@NotNull
	@OneToMany(mappedBy="votacion",cascade=CascadeType.ALL)
	public List<Voto> getVotos() {
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
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
