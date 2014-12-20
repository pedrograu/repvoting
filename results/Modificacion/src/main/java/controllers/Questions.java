package controllers;

import java.io.Serializable;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Questions implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = -8952857982032416594L;
	private Number id;
   	private String text;

 	@Override
	public String toString() {
		return "Questions [id=" + id + ", text=" + text + "]";
	}
	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public String getText(){
		return this.text;
	}
	public void setText(String text){
		this.text = text;
	}
}
