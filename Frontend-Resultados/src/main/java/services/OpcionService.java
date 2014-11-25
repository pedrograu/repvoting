package services;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

	public Opcion save(Opcion o) {
		Set<String> opcionesEnBDD = getOpcionesEnBDD();
		Opcion res;
		if (!opcionesEnBDD.contains(o.getNombre())) {
			res = opcionRepository.save(o);// añadimos opciones no persistidas
											// aun
		} else {
			res = findOpcionPorNombre(o.getNombre());// si ya existe la
			// obtenemos de la base de datos para coger
			// su campos id y poder asociarlo al voto a
			// la hora de guardarpara que no de error
		}

		return res;
	}

	public Opcion findOpcionPorNombre(String nombre) {
		return opcionRepository.findOpcionPorNombre(nombre);
	}

	public Set<String> getOpcionesEnBDD() {
		return new HashSet<String>(opcionRepository.findOpcionesEnBDD());
	}

	public List<Opcion> save(List<Opcion> opciones) {
		Set<String> opcionesEnBDD = getOpcionesEnBDD();
		List<Opcion> res = new LinkedList<Opcion>();
		for (Opcion opcion : opciones) {
			if (!opcionesEnBDD.contains(opcion.getNombre())) {
				res.add(opcion);// añadimos opciones no persistidas aun
			}
		}
		return opcionRepository.save(res);
	}

	public Opcion find(Integer idOpcion) {

		return opcionRepository.findOne(idOpcion);
	}

}
