package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.VotacionRepository;
import domain.Votacion;
import domain.Voto;

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

	public Object[] ParseoDatosVisualizacion(Votacion votacion) {
		int cont = 0;
		Object[] res = new Object[votacion.getVotos().size()];

		for (Voto v : votacion.getVotos()) {
			Object res2[] = new Object[2];
			res2[0] = v.getOpcion().getNombre();
			res2[1] = v.getNum_votos();
			res[cont] = res2;
			cont++;
		}
		return res;
	}

	public Votacion find(Integer idvotacion) {
		if (idvotacion == null)
			return null;
		else {
			return votacionRepository.findOne(idvotacion);
		}
	}
}
