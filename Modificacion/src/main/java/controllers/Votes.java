package controllers;
import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Votes implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = -7254944578442391483L;
	private String description;
   	@Override
	public String toString() {
		return "Votes [description=" + description + ", endDate=" + endDate
				+ ", id=" + id + ", questions=" + questions + ", startDate="
				+ startDate + ", title=" + title + "]";
	}
	private String endDate;
   	private String id;
   	private List<Questions> questions;
   	private String startDate;
   	private String title;
 
 	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
 	public String getEndDate(){
		return this.endDate;
	}
	public void setEndDate(String endDate){
		this.endDate = endDate;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public List<Questions> getQuestions(){
		return this.questions;
	}
	public void setQuestions(List<Questions> questions){
		this.questions = questions;
	}
 	public String getStartDate(){
		return this.startDate;
	}
	public void setStartDate(String startDate){
		this.startDate = startDate;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
}
