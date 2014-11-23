package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.VotacionRepository;
import domain.Votacion;


@Transactional
@Service
public class VotacionService {

	// Managed repository

	@Autowired
	private VotacionRepository votacionRepository;


	// Supporting services

	

	// Simple CRUD methods


	public void save(Votacion votacion) {
		
		votacionRepository.save(votacion);
	}
	public Votacion find(Integer idvotacion) {
		
		return votacionRepository.findOne(idvotacion);
	}
	

	

}
