package domain;

public class Respuesta {

	private String pregunta;
	private int numerosSi;
	private int numerosNo;

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public int getNumerosSi() {
		return numerosSi;
	}

	public void setNumerosSi(int numerosSi) {
		this.numerosSi = numerosSi;
	}

	public int getNumerosNo() {
		return numerosNo;
	}

	public void setNumerosNo(int numerosNo) {
		this.numerosNo = numerosNo;
	}

	@Override
	public String toString() {
		return "Respuesta [pregunta=" + pregunta + ", numerosSi=" + numerosSi
				+ ", numerosNo=" + numerosNo + "]";
	}

}
