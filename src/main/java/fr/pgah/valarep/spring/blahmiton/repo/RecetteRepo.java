package fr.pgah.valarep.spring.blahmiton.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import fr.pgah.valarep.spring.blahmiton.model.Recette;

@Repository
public interface RecetteRepo extends CrudRepository<Recette, Long> {

}
