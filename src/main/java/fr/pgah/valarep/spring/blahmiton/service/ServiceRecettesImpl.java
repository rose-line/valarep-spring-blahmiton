package fr.pgah.valarep.spring.blahmiton.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;
import fr.pgah.valarep.spring.blahmiton.commandobj.RecetteCommandObj;
import fr.pgah.valarep.spring.blahmiton.converter.CommandToRecette;
import fr.pgah.valarep.spring.blahmiton.converter.RecetteToCommand;
import fr.pgah.valarep.spring.blahmiton.model.Recette;
import fr.pgah.valarep.spring.blahmiton.repo.RecetteRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class ServiceRecettesImpl implements ServiceRecettes {

  private RecetteRepo recetteRepo;
  private CommandToRecette commandToRecette;
  private RecetteToCommand recetteToCommand;

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

  @Override
  public RecetteCommandObj findCommandById(Long id) {
    Recette recette = findById(id);
    return recetteToCommand.convert(recette);
  }

  @Override
  public RecetteCommandObj saveRecetteCommandObj(RecetteCommandObj recetteCmd) {

    Recette recette = commandToRecette.convert(recetteCmd);

    Recette recetteEnregistree = recetteRepo.save(recette);

    log.info("Id de la recette enregistree : " + recetteEnregistree.getId());

    return recetteToCommand.convert(recetteEnregistree);
  }

  @Override
  public void deleteById(Long id) {
    recetteRepo.deleteById(id);
  }
}
