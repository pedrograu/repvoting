package controllers;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Respuesta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String pregunta;
	private String numerosSi;
	private String numerosNo;
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getNumerosSi() {
		return numerosSi;
	}
	public void setNumerosSi(String numerosSi) {
		this.numerosSi = numerosSi;
	}
	public String getNumerosNo() {
		return numerosNo;
	}
	public void setNumerosNo(String numerosNo) {
		this.numerosNo = numerosNo;
	}
	
	
	@Override
	public String toString() {
		return "Respuesta [pregunta=" + pregunta + ", numerosSi=" + numerosSi
				+ ", numerosNo=" + numerosNo + "]";
	}
	
	

}
