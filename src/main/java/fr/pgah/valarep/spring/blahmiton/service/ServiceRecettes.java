package fr.pgah.valarep.spring.blahmiton.service;

import java.util.Set;
import fr.pgah.valarep.spring.blahmiton.model.Recette;

public interface ServiceRecettes {

  Set<Recette> getRecettes();

  Recette findById(long id);

}
