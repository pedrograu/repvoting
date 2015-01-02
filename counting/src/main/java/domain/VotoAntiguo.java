package domain;

public class VotoAntiguo {

	private String nombre; // This string returns the name of the vote, not the voter.
	private String region;
	private Integer edad;
	private String poblacion;

	// Getters and setters.

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String n) {
		this.nombre = n;
	}

	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer e) {
		this.edad = e;
	}

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String p) {
		this.poblacion = p;
	}

	
	
	
	public String getRegion() {
		return region;
	}

	
	
	
	public void setRegion(String region) {
		this.region = region;
	}
	
	
}
