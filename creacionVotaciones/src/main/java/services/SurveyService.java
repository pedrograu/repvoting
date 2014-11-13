package services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SurveyRepository;
import domain.Census;
import domain.Survey;
@Service
public class SurveyService {

	//Repositories
	@Autowired
	private SurveyRepository surveyRepository;
	
	//Services
	@Autowired
	private QuestionService questionService;
	
	@Transactional
	public void save(Survey s){
		String cookie = ""; //COOKIE DE LA CONEXIÓN
		Assert.notNull(s);
//		Assert.isTrue(isAuthenticated(cookie));
		surveyRepository.save(s);
	}
	
	//Método de interacción con el subsistema de Creacion de Censos
	public void addCensus(Survey s, Census c){
		Assert.notNull(c);
		Assert.notNull(s);
		
		Assert.isTrue(s.getCensus()==null);
		s.setCensus(c);
		surveyRepository.save(s);
		
	}
	
	//Método de interacción con el subsistema de Visualización
	public List<Survey>allFinishedSurveys(){
		
		Date now = new Date(System.currentTimeMillis());
		List<Survey>res = surveyRepository.allFinishedSurveys(now);
		return res;
	}
	
	public List<Survey>allCreatedSurveys(String usernameCreator){
		List<Survey>res = surveyRepository.allCreatedSurveys(usernameCreator);
		return res;
	}
}
