/* CustomerController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import services.OpcionService;
import services.VotacionService;
import services.VotoService;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Opcion;
import domain.Votacion;
import domain.Voto;

@Controller
@RequestMapping("/rest/votacion")
public class VotacionController extends AbstractController {
	@Autowired
	VotacionService votacionService;

	@Autowired
	VotoService votoService;

	@Autowired
	OpcionService opcionService;

	// Constructors -----------------------------------------------------------

	public VotacionController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Votacion get(@PathVariable("id") int idVotacion) {
		Votacion votacion = votacionService.find(idVotacion);
		return votacion;
	}

	@RequestMapping(value = "/get2/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Object[] get2(@PathVariable("id") int idVotacion) {
		Votacion votacion = votacionService.find(idVotacion);
		Object res[] = votacionService.ParseoDatosVisualizacion(votacion);

		return res;

	}

	@RequestMapping(value = "/getPrueba/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Integer> pruebaSupuestoGetQueHacemosARecuento(
			@PathVariable("id") int idVotacion) {
		Votacion votacion = votacionService.find(idVotacion);

		Map<String, Integer> jsonMap = new HashMap<String, Integer>();

		for (Voto v : votacion.getVotos()) {
			jsonMap.put(v.getOpcion().getNombre(), v.getNum_votos());
		}
		return jsonMap;

	}

	@RequestMapping(value = "/postBueno", method = RequestMethod.GET)
	public @ResponseBody
	Object[] parseJson(@RequestParam Integer idVotacion) {

		Map<String, Integer> jsonMap = new HashMap<String, Integer>();
		RestTemplate restTemplate = new RestTemplate();
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		TypeReference<HashMap<String, Integer>> typeRef = new TypeReference<HashMap<String, Integer>>() {
		};
		// esto es para probar el codigo real seria el comentado de los if y
		// else de abajo
		String json = restTemplate
				.getForObject(
						"http://localhost:8080/Frontend-Resultados/rest/votacion/getPrueba/14.do",
						String.class);
		if (idVotacion == null) {
			/*
			 * 
			 * String json = restTemplate .getForObject(
			 * "http://localhost:8080/test/recuento2?idVotacion=",
			 * String.class);
			 */} else {
			/*
			 * 
			 * String json = restTemplate .getForObject(
			 * "http://localhost:8080/test/recuento2?idVotacion=" + idVotacion,
			 * String.class);
			 */
		}
		try {
			// convert JSON string to Map
			jsonMap = mapper.readValue(json, typeRef);

		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Error al obtener los datos de la votación:\n" + json);
		}

		List<Opcion> opciones = new LinkedList<Opcion>();
		List<Voto> votos = new LinkedList<Voto>();
		Votacion votacion;

		votacion = votacionService.find(idVotacion);
		if (votacion == null) {
			// Es decir es una votación nueva eqivale a que no nos pasen el id
			// osea la peticion
			// quedaria:http://localhost:8080/test/recuento2?idVotacion=
			// por eso lo tomamos como integer para que pueda tomar
			// valor null, ya que los int al ser primitivos no pueden ser null

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
			votacionService.save(votacion);

		}
		// el return devuelve un json con la votacion indicada, hasta que
		// aclaremos para quien va esta informacion
		// Realmente este metodo nos sirve para consumir informacion y mandarla
		// no haria falta los metodos de get anteriores ya que este mismo si
		// comprueba que la id es nula ya sabe que se trata de un guardar datos
		// en la base de datos
		// en cambio si el id es conocido y esta el la base de datos lo extrae y
		// muestra
		Object[] res = votacionService.ParseoDatosVisualizacion(votacion);
		return res;

	}

	@RequestMapping(value = "/get/prueba")
	public ModelAndView index(
			@RequestParam(required = false, defaultValue = "John Doe") String name) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());

		result = new ModelAndView("welcome/index");
		result.addObject("name", name);
		result.addObject("moment", moment);

		return result;
	}

}