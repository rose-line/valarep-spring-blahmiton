package fr.pgah.valarep.spring.blahmiton.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import fr.pgah.valarep.spring.blahmiton.model.Categorie;

@Repository
public interface CategorieRepo extends CrudRepository<Categorie, Long> {

  List<Categorie> findByNom(String nom);

}
