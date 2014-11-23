package domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class Voto implements Serializable {
	/**
* 
*/
	private static final long serialVersionUID = 1L;
	Integer id;
	String opcion;
	Integer num_votos;
	Date createdDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public Integer getNum_votos() {
		return num_votos;
	}

	public void setNum_votos(Integer num_votos) {
		this.num_votos = num_votos;
	}

	@JsonSerialize(using=DateSerializer.class)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
