package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import domain.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto,Integer> {
	
	

}
