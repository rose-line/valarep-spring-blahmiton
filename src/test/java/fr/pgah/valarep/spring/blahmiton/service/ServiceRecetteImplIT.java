package fr.pgah.valarep.spring.blahmiton.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import fr.pgah.valarep.spring.blahmiton.commandobj.RecetteCommandObj;
import fr.pgah.valarep.spring.blahmiton.converter.RecetteToCommand;
import fr.pgah.valarep.spring.blahmiton.model.Recette;
import fr.pgah.valarep.spring.blahmiton.repo.RecetteRepo;

@SpringBootTest
public class ServiceRecetteImplIT {

  @Autowired
  ServiceRecettes service;

  @Autowired
  RecetteRepo repo;

  @Autowired
  RecetteToCommand recetteToCommand;

  @Transactional
  @Test
  void testModificationNomRecette() {

    // Given

    Iterable<Recette> recettes = repo.findAll();
    Recette uneRecette = recettes.iterator().next();
    RecetteCommandObj uneRecetteCmd = recetteToCommand.convert(uneRecette);
    String nomAttendu = "le nouveau nom";
    uneRecetteCmd.setDescription(nomAttendu);

    // When

    RecetteCommandObj laCmdEnregistree = service.saveRecetteCommandObj(uneRecetteCmd);

    // Then

    assertEquals(nomAttendu, laCmdEnregistree.getDescription());
    assertEquals(uneRecette.getId(), laCmdEnregistree.getId());
    assertEquals(uneRecette.getCategories().size(), laCmdEnregistree.getCategories().size());
    assertEquals(uneRecette.getIngredients().size(), laCmdEnregistree.getIngredients().size());
  }
}
