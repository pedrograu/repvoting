package controllers;
import java.util.List;

public class EncuestaBD {

	private List<Voto> votos;

	public enum Criterio {

		CIUDAD, SEXO, PROVINCIA, EDAD, COMUNIDAD, SITUACION, NACIONALIDAD;

	}

	public EncuestaBD() {
	}

	public List<Voto> getListaVoto() {
		return votos;
	}

}
