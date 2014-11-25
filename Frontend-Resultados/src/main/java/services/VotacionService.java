package services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import repositories.VotacionRepository;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Opcion;
import domain.Votacion;
import domain.Voto;

@Transactional
@Service
public class VotacionService {

	// Managed repository
	@Autowired
	VotoService votoService;

	@Autowired
	OpcionService opcionService;

	@Autowired
	private VotacionRepository votacionRepository;

	// Supporting services

	// Simple CRUD methods

	public Votacion save(Votacion votacion) {

		return votacionRepository.save(votacion);
	}

	public Object[] ParseoDatosVisualizacion(Votacion votacion) {
		int cont = 0;
		Object[] res = new Object[votacion.getVotos().size()];

		for (Voto v : votacion.getVotos()) {
			Object res2[] = new Object[2];
			res2[0] = v.getOpcion().getNombre();
			res2[1] = v.getNum_votos();
			res[cont] = res2;
			cont++;
		}
		return res;
	}

	public Votacion find(Integer idvotacion) {
		if (idvotacion == null)
			return null;
		else {
			return votacionRepository.findOne(idvotacion);
		}
	}

	public Votacion getVotacionDeRecuento(Integer idVotacion) {

		RestTemplate restTemplate = new RestTemplate();

		// esto es para probar se comunica con nosotros mismo sin importar el
		// idVotacion y muestra la que tenemos almacenada en la base de datos
		// pero haciendolo creer que no la tenemos que es de recuento de forma
		// que podemos comporabar que captura bien el json
		// el codigo que debe de ir es el comentado de abajo

		String jsonString = restTemplate
				.getForObject(
						"http://localhost:8080/Frontend-Resultados/rest/votacion/getPrueba/14.do",
						String.class);

		/*
		 * String jsonString = restTemplate .getForObject(
		 * "http://localhost:8080/test/recuento2?idVotacion=" + idVotacion,
		 * String.class);
		 */
		return parseoJsonAVotacion(jsonString);
	}

	public Votacion parseoJsonAVotacion(String jsonString) {
		Votacion votacion;
		Map<String, Integer> jsonMap;

		votacion = new Votacion();
		jsonMap = new HashMap<String, Integer>();

		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		TypeReference<HashMap<String, Integer>> typeRef = new TypeReference<HashMap<String, Integer>>() {
		};

		List<Opcion> opciones = new LinkedList<Opcion>();
		List<Voto> votos = new LinkedList<Voto>();
		try {
			// convert JSON string to Map
			jsonMap = mapper.readValue(jsonString, typeRef);

		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Error al obtener los datos de la votación:\n" + jsonString);
		}
		for (String opcionString : jsonMap.keySet()) {
			Opcion opcion = new Opcion(opcionString);

			opcion = opcionService.save(opcion);
			Voto v = new Voto();
			Integer numeroVotos = jsonMap.get(opcionString);
			v.setNum_votos(numeroVotos);

			v.setOpcion(opcion);

			opciones.add(opcion);

			votos.add(v);

		}
		votos = votoService.save(votos);

		votacion = new Votacion();
		// Segun lo entendido votacion solo debe tener un campo votos y una
		// id
		// pero lo dejo tal y como teniamos planteado
		votacion.setNombre("Votacion");
		votacion.setOpciones(opciones);
		votacion.setVotos(votos);
		return save(votacion);
	}
}
