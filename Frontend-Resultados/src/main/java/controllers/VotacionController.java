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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import domain.Opcion;
import domain.Votacion;
import domain.Voto;
import services.OpcionService;
import services.VotacionService;
import services.VotoService;

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
	public @ResponseBody Votacion get(@PathVariable("id") int idVotacion) {
			Votacion votacion= votacionService.find(idVotacion);
		return votacion;
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
    public @ResponseBody Votacion createVotacion(@RequestBody Votacion vot) {
		
		
		List<Opcion> opciones = new ArrayList<Opcion>();
		for(Opcion op : vot.getOpciones())
		{
			opciones.add(new Opcion(op.getNombre()));
		}
		
		 opciones = opcionService.save(opciones);
		 
		List<Voto> votos = new ArrayList<Voto>();
		for(Voto vo : vot.getVotos())
		{
			Opcion elegida=null;
			Voto v = new Voto();
			v.setNum_votos(vo.getNum_votos());
			for(Opcion op : opciones)
			{
				if(op.getNombre().equals(vo.getOpcion().getNombre()))
				{
					elegida=op;
					break;
				}
			}
		   
			v.setOpcion(elegida);
			votos.add(v);
		}
		
		votos = votoService.save(votos);
		Votacion vots;
		vots= new Votacion();
		vots.setNombre(vot.getNombre());
		vots.setOpciones(opciones);
		vots.setVotos(votos);
		votacionService.save(vots);
       
        return vot;
    }
	@RequestMapping(value = "/get/prueba")
	public ModelAndView index(@RequestParam(required=false, defaultValue="John Doe") String name) {
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