package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Opcion;

@Repository
public interface OpcionRepository extends JpaRepository<Opcion, Integer> {

	@Query("select op.nombre from Opcion op")
	Collection<String> findOpcionesEnBDD();

	@Query("select op from Opcion op where op.nombre=?1")
	Opcion findOpcionPorNombre(String nombre);

}
