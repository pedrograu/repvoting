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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import services.OpcionService;
import services.VotacionService;
import services.VotoService;
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

	@RequestMapping(value = "/getVotacion", method = RequestMethod.GET)
	public @ResponseBody
	Object[] getVotacion(@RequestParam Integer idVotacion) {
		Votacion votacion;

		if (idVotacion == null) {
			// la peticion debe indicar una id, sin ella no sabemos a que
			// votacion se refiere
			throw new IllegalArgumentException(
					"No se ha indicado correctamente el id de la votacion");
		}
		votacion = votacionService.find(idVotacion);
		// si no es nula es que ya la hemos obtenido y guardado anteriormente,
		// simplemente la mandamos
		if (votacion == null) {

			// Es decir es una votación nueva, por lo que nos comunicamos con
			// recuento
			votacion = votacionService.getVotacionDeRecuento(idVotacion);

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