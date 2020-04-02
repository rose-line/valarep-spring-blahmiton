package fr.pgah.valarep.spring.blahmiton.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import fr.pgah.valarep.spring.blahmiton.model.UniteDeMesure;

@Repository
public interface UniteDeMesureRepo extends CrudRepository<UniteDeMesure, Long> {

  List<UniteDeMesure> findByNom(String nom);

}
