package domain;

public class Resultado {
	String pregunta;
	Integer numeroSi;
	Integer numeroNo;
	public Resultado(String pregunta, Integer numeroSi, Integer numeroNo) {
		super();
		this.pregunta = pregunta;
		this.numeroSi = numeroSi;
		this.numeroNo = numeroNo;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public Integer getNumeroSi() {
		return numeroSi;
	}
	public void setNumeroSi(Integer numeroSi) {
		this.numeroSi = numeroSi;
	}
	public Integer getNumeroNo() {
		return numeroNo;
	}
	public void setNumeroNo(Integer numeroNo) {
		this.numeroNo = numeroNo;
	}
	
	
}
