package domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.AccessType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
	@OneToMany
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
	

}
