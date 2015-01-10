package domain;

import java.util.List;

public class Votos {

	private Integer msg;
	private List<Voto> votes;

	public Integer getMsg() {
		return this.msg;
	}

	public void setMsg(Integer msg) {
		this.msg = msg;
	}

	public List<Voto> getVotes() {
		return votes;
	}

	public void setVotes(List<Voto> votes) {
		this.votes = votes;
	}

}
