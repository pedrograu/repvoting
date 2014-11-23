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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import domain.Votacion;
import services.VotacionService;

@Controller
@RequestMapping("/rest/votacion")
public class VotacionController extends AbstractController {

	@Autowired
	VotacionService votacionService;
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
	
	
}