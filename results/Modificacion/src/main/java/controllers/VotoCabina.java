
package controllers;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VotoCabina implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = 2262806707244617338L;
	private String age;
   	private String answers;
   	private String community;
   	private String genre;
   	private String id;
   	private String id_poll;

 	public String getAge(){
		return this.age;
	}
	public void setAge(String age){
		this.age = age;
	}
 	public String getAnswers(){
		return this.answers;
	}
	public void setAnswers(String answers){
		this.answers = answers;
	}
 	public String getCommunity(){
		return this.community;
	}
	public void setCommunity(String community){
		this.community = community;
	}
 	public String getGenre(){
		return this.genre;
	}
	public void setGenre(String genre){
		this.genre = genre;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getId_poll(){
		return this.id_poll;
	}
	public void setId_poll(String id_poll){
		this.id_poll = id_poll;
	}
}
