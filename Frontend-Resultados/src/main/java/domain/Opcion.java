package domain;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;



@Entity
@Access(AccessType.PROPERTY)
public class Opcion extends DomainEntity implements Serializable {
	
// Constructors -----------------------------------------------------------

	public Opcion() {
		super();
	}

// Attributes -------------------------------------------------------------

	public Opcion(String nombre) {
		super();
		this.nombre=nombre;
	}

	private static final long serialVersionUID = 14364567564L;
	String nombre;
	
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
