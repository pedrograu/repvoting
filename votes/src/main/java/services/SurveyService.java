package services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SurveyRepository;
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
	public Integer save(Survey s){
		Assert.notNull(s);
		Survey s1 = surveyRepository.save(s);
		return s1.getId();
	}
	
	public Survey findOne(int id){
		Assert.notNull(id);
		return surveyRepository.findOne(id);
	}
	//M�todo de interacci�n con el subsistema de Creacion de Censos
	public void addCensus(Integer s, Integer c){
		Survey survey = findOne(s);
		Assert.notNull(c);
		Assert.notNull(survey);
		
		Assert.isTrue(survey.getCensus()==null);
		survey.setCensus(c);
		surveyRepository.save(survey);
		
	}
	
	//M�todo de interacci�n con el subsistema de Visualizaci�n
	public List<Survey>allFinishedSurveys(){
		
		Date now = new Date(System.currentTimeMillis());
		List<Survey>res = surveyRepository.allFinishedSurveys(now);
		return res;
	}
	
	public List<Survey>allCreatedSurveys(String usernameCreator){
		List<Survey>res = surveyRepository.allCreatedSurveys(usernameCreator);
		return res;
	}
	
	public void delete(int id){
		Assert.notNull(id);
		surveyRepository.delete(id);
	}
	
	public Boolean posible (int id) {
		Assert.notNull(id);
		Survey s = findOne(id);
		
		if(s.getCensus()==null){
			return true;
		}else{
			return false;
		}
	}
}
