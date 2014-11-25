package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.VotoRepository;
import domain.Voto;

@Transactional
@Service
public class VotoService {

	// Managed repository

	@Autowired
	private VotoRepository votoRepository;

	// Supporting services

	// Simple CRUD methods

	public void save(Voto voto) {

		votoRepository.save(voto);
	}

	public List<Voto> save(List<Voto> votos) {

		return votoRepository.save(votos);
	}

	public Voto find(Integer idvoto) {

		return votoRepository.findOne(idvoto);
	}

}
