package domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Voto extends DomainEntity implements Serializable {

	// Constructors -----------------------------------------------------------

	public Voto() {
		super();

	}

	// Attributes -------------------------------------------------------------

	private static final long serialVersionUID = 1L;
	Opcion opcion;
	Integer num_votos;

	@Valid
	@NotNull
	@ManyToOne
	public Opcion getOpcion() {
		return opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}

	public Integer getNum_votos() {
		return num_votos;
	}

	public void setNum_votos(Integer num_votos) {
		this.num_votos = num_votos;
	}

}
