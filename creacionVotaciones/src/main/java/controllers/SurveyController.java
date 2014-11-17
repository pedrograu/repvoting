package controllers;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import services.SurveyService;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Survey;

@RestController
@RequestMapping("/vote")
public class SurveyController {

	// Services
	@Autowired
	private SurveyService surveyService;

	// Methods

	// M�todo para guardar la votaci�n creada.
	@RequestMapping(value = "/save", method = RequestMethod.POST, headers = "Content-Type=application/json")
	public @ResponseBody String save(@RequestBody String surveyJson)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Survey s = mapper.readValue(surveyJson, Survey.class);
		int i = surveyService.save(s);
		return "{\"id\":\""+i+"\", \"fecha_ini\":\""+s.getStartDate()+"\", \"fecha_fin\":\""+s.getEndDate()+"\", \"tituloVotacion\":\""+s.getTitle()+"\" }";
	}

	// M�todo para guardar la votaci�n con el censo. Relaci�n con CREACION/ADMINISTRACION DE CENSO.
	@RequestMapping(value = "/saveCensus", method = RequestMethod.POST, headers = "Content-Type=application/json")
	public @ResponseBody void saveCensus(@RequestBody String surveyJson)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Survey s = mapper.readValue(surveyJson, Survey.class);
		surveyService.save(s);
	}

	// M�todo que devuelve la lista de votaciones creadas para editarlas.
	// Relaci�n con CREACION/ADMINISTRACION DE CENSO.
	@RequestMapping(value = "/mine", method = RequestMethod.GET)
	public Collection<Survey> findAllSurveyByCreator() {
		String creator = "admin";
		Collection<Survey> res = surveyService.allCreatedSurveys(creator);
		return res;
	}

	// M�todo que devuelve la lista de votaciones finalizadas. Relaci�n con
	// VISUALIZACION.
	@RequestMapping(value = "/finishedSurveys", method = RequestMethod.GET)
	public Collection<Survey> findAllfinishedSurveys() {
		Collection<Survey> res = surveyService.allFinishedSurveys();
		return res;
	}

	// M�todo que borra una votaci�n tras comprobar que no tiene censo relacionado.
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public void delete(@RequestParam int id) {
		Boolean posible = surveyService.posible(id);
		if (posible == true) {
			surveyService.delete(id);
		} else {
			System.out
					.println("La votaci�n no puede ser eliminada, tiene un censo relacionado.");
		}
	}

	// M�todo devuelve una survey para realizar una votaci�n. Relaci�n con CABINA DE VOTACION
	@RequestMapping(value="/survey", method = RequestMethod.GET)
	public Survey getSurvey(@RequestParam int id) {
		Survey s = surveyService.findOne(id);
		return s;
	}

}
