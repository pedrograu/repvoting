package controllers;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	//Services
	@Autowired
	private SurveyService surveyService;
	
	//Methods
	
	@RequestMapping(value="/save", method=RequestMethod.POST, headers="Content-Type=application/json")
	public @ResponseBody void save(@RequestBody String surveyJson) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		Survey s = mapper.readValue(surveyJson,Survey.class); 
		surveyService.save(s);
		System.out.println(surveyJson);
		System.out.println(s);
	}
	
	@RequestMapping(value="/mine", method=RequestMethod.GET)
	public Collection<Survey> findAllSurveyByCreator(){
		String creator = "admin";
		Collection <Survey> res = surveyService.allCreatedSurveys(creator);
		System.out.println(res);
		return res;
	}
	
}
