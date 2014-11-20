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

	// Método para guardar la votación creada.
	@RequestMapping(value = "/save", method = RequestMethod.POST, headers = "Content-Type=application/json")
	public @ResponseBody Survey save(@RequestBody String surveyJson)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Survey s = mapper.readValue(surveyJson, Survey.class);
		int i = surveyService.save(s);
		Survey res = surveyService.findOne(i);
//		return "{\"id\":\""+i+"\", \"fecha_ini\":\""+s.getStartDate().getDay()+"/"+s.getStartDate().getMonth()+"/"+s.getStartDate().getY+"\", \"fecha_fin\":\""+s.getEndDate()+"\", \"tituloVotacion\":\""+s.getTitle()+"\" }";
		return res;
	}

	// Método para guardar la votación con el censo. Relación con CREACION/ADMINISTRACION DE CENSO.
	@RequestMapping(value = "/saveCensus", method = RequestMethod.POST, headers = "Content-Type=application/json")
	public @ResponseBody void saveCensus(@RequestBody String surveyJson)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Survey s = mapper.readValue(surveyJson, Survey.class);
		surveyService.save(s);
	}

	// Método que devuelve la lista de votaciones creadas para editarlas.
	// Relación con CREACION/ADMINISTRACION DE CENSO.
	@RequestMapping(value = "/mine", method = RequestMethod.GET)
	public Collection<Survey> findAllSurveyByCreator() {
		String creator = "admin";
		Collection<Survey> res = surveyService.allCreatedSurveys(creator);
		return res;
	}

	// Método que devuelve la lista de votaciones finalizadas. Relación con
	// VISUALIZACION.
	@RequestMapping(value = "/finishedSurveys", method = RequestMethod.GET)
	public Collection<Survey> findAllfinishedSurveys() {
		Collection<Survey> res = surveyService.allFinishedSurveys();
		return res;
	}

	// Método que borra una votación tras comprobar que no tiene censo relacionado.
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public void delete(@RequestParam int id) {
		Boolean posible = surveyService.posible(id);
		if (posible == true) {
			surveyService.delete(id);
		} else {
			System.out
					.println("La votación no puede ser eliminada, tiene un censo relacionado.");
		}
	}

	// Método devuelve una survey para realizar una votación. Relación con CABINA DE VOTACION
	@RequestMapping(value="/survey", method = RequestMethod.GET)
	public Survey getSurvey(@RequestParam int id) {
		Survey s = surveyService.findOne(id);
		return s;
	}

}
