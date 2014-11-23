package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.OpcionRepository;
import domain.Opcion;


@Transactional
@Service
public class OpcionService {

	// Managed repository

	@Autowired
	private OpcionRepository opcionRepository;


	// Supporting services

	

	// Simple CRUD methods


	public void save(Opcion o) {
		
		opcionRepository.save(o);
	}
	
	public List<Opcion> save(List<Opcion> opciones) {
		
		return opcionRepository.save(opciones);
	}
	
	public Opcion find(Integer idOpcion) {
		
		return opcionRepository.findOne(idOpcion);
	}
	

	

}
