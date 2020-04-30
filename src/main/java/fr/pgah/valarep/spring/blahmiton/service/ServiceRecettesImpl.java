package fr.pgah.valarep.spring.blahmiton.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.pgah.valarep.spring.blahmiton.model.Recette;
import fr.pgah.valarep.spring.blahmiton.repo.RecetteRepo;

@Service
public class ServiceRecettesImpl implements ServiceRecettes {

  @Autowired
  private RecetteRepo recetteRepo;

  public ServiceRecettesImpl(RecetteRepo recetteRepo) {
    this.recetteRepo = recetteRepo;
  }

  @Override
  public Set<Recette> getRecettes() {
    Set<Recette> recettes = new HashSet<>();

    for (Recette recette : recetteRepo.findAll()) {
      recettes.add(recette);
    }

    // recetteRepo.findAll().iterator().forEachRemaining(recette -> recettes.add(recette));
    // recetteRepo.findAll().iterator().forEachRemaining(recettes::add);

    return recettes;
  }

  @Override
  public Recette findById(long id) {
    return recetteRepo.findById(id).get();
  }
}
