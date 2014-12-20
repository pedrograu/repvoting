package controllers;

import java.util.List;

public class Voto {
   	private Integer msg;
   	private List<String> votosCifrados;

 	public Integer getMsg(){
		return this.msg;
	}
	public void setMsg(Integer msg){
		this.msg = msg;
	}
 	public List<String> getVotes(){
		return this.votosCifrados;
	}
	public void setVotes(List<String> votes){
		this.votosCifrados = votes;
	}
}