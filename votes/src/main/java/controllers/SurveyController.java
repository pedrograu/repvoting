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
	public @ResponseBody Survey save(@RequestBody String surveyJson)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Survey s = mapper.readValue(surveyJson, Survey.class);
		int i = surveyService.save(s);
		Survey res = surveyService.findOne(i);
//		return "{\"id\":\""+i+"\", \"fecha_ini\":\""+s.getStartDate().getDay()+"/"+s.getStartDate().getMonth()+"/"+s.getStartDate().getY+"\", \"fecha_fin\":\""+s.getEndDate()+"\", \"tituloVotacion\":\""+s.getTitle()+"\" }";
		return res;
	}

	// M�todo para guardar la votaci�n con el censo. Relaci�n con CREACION/ADMINISTRACION DE CENSO.
	@RequestMapping(value = "/saveCensus", method = RequestMethod.GET)
	public @ResponseBody void saveCensus(@RequestParam Integer surveyId, @RequestParam Integer censusId)
			throws JsonParseException, JsonMappingException, IOException {
		surveyService.addCensus(surveyId, censusId);
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
			surveyService.delete(id);
	}

	// M�todo devuelve una survey para realizar una votaci�n. Relaci�n con CABINA DE VOTACION
	@RequestMapping(value="/survey", method = RequestMethod.GET)
	public Survey getSurvey(@RequestParam int id) {
		Survey s = surveyService.findOne(id);
		return s;
	}

}
