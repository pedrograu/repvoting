package domain;

import java.util.Date;
import java.util.Map;


public class CensusUser {
	
	
	int idVotacion;
	String username;
	boolean result;
	int version;
	int id;
	String tituloVotacion;
	Date fechaInicioVotacion;
	Date fechaFinVotacion;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTituloVotacion() {
		return tituloVotacion;
	}


	public void setTituloVotacion(String tituloVotacion) {
		this.tituloVotacion = tituloVotacion;
	}


	public Date getFechaInicioVotacion() {
		return fechaInicioVotacion;
	}


	public void setFechaInicioVotacion(Date fechaInicioVotacion) {
		this.fechaInicioVotacion = fechaInicioVotacion;
	}


	public Date getFechaFinVotacion() {
		return fechaFinVotacion;
	}


	public void setFechaFinVotacion(Date fechaFinVotacion) {
		this.fechaFinVotacion = fechaFinVotacion;
	}


	String nombre_votacion;
	int votacion_id;
	Map<String,Boolean> voto_por_usuario;
	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


	public String getNombre_votacion() {
		return nombre_votacion;
	}


	public void setNombre_votacion(String nombre_votacion) {
		this.nombre_votacion = nombre_votacion;
	}


	public int getVotacion_id() {
		return votacion_id;
	}


	public void setVotacion_id(int votacion_id) {
		this.votacion_id = votacion_id;
	}


	public Map<String, Boolean> getVoto_por_usuario() {
		return voto_por_usuario;
	}


	public void setVoto_por_usuario(Map<String, Boolean> voto_por_usuario) {
		this.voto_por_usuario = voto_por_usuario;
	}


	public Date getFecha_inicio() {
		return fecha_inicio;
	}


	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}


	public Date getFecha_fin() {
		return fecha_fin;
	}


	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}


	Date fecha_inicio;
	Date fecha_fin;
	
	public int getIdVotacion() {
		return idVotacion;
	}


	public void setIdVotacion(int idVotacion) {
		this.idVotacion = idVotacion;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public boolean isResult() {
		return result;
	}


	public void setResult(boolean result) {
		this.result = result;
	}


	public CensusUser(){
		
		
	}

	
	
	
	
}
