package controllers;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VotosTest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer msg;
	private List<VotoTest> votes;

	public Integer getMsg() {
		return this.msg;
	}

	public void setMsg(Integer msg) {
		this.msg = msg;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<VotoTest> getVotes() {
		return votes;
	}

	public void setVotes(List<VotoTest> votes) {
		this.votes = votes;
	}

	
}
