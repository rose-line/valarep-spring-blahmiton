package fr.pgah.valarep.spring.blahmiton.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import fr.pgah.valarep.spring.blahmiton.commandobj.RecetteCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Categorie;
import fr.pgah.valarep.spring.blahmiton.model.Commentaire;
import fr.pgah.valarep.spring.blahmiton.model.Difficulte;
import fr.pgah.valarep.spring.blahmiton.model.Ingredient;
import fr.pgah.valarep.spring.blahmiton.model.Recette;

public class RecetteToCommandTests {

  @Test
  public void convertirNull_DevraitRetournerNull() throws Exception {
    RecetteToCommand converter = new RecetteToCommand(
        new CommentaireToCommand(),
        new CategorieToCommand(),
        new IngredientToCommand(new UniteDeMesureToCommand()));

    RecetteCommandObj command = converter.convert(null);

    assertNull(command, "Le CommandObj devrait être null");
  }

  @Test
  public void convertirRecetteVide_DevraitRetournerCommandVide() throws Exception {
    RecetteToCommand converter = new RecetteToCommand(
        new CommentaireToCommand(),
        new CategorieToCommand(),
        new IngredientToCommand(new UniteDeMesureToCommand()));

    RecetteCommandObj command = converter.convert(new Recette());

    assertNotNull(command, "Le CommandObj ne devrait pas être null");
  }

  @Test
  public void convertirRecettePeuple_DevraitRetournerCommandPeuple() throws Exception {

    // Given

    RecetteToCommand converter = new RecetteToCommand(
        new CommentaireToCommand(),
        new CategorieToCommand(),
        new IngredientToCommand(new UniteDeMesureToCommand()));

    Recette recette = new Recette();
    Long idAttendue = 1L;
    recette.setId(idAttendue);
    String descAttendue = "La recette";
    recette.setDescription(descAttendue);
    int tempsPrepAttendu = 15;
    recette.setTempsPrep(tempsPrepAttendu);
    int tempsCuissonAttendu = 30;
    recette.setTempsCuisson(tempsCuissonAttendu);
    Difficulte diffAttendue = Difficulte.DIFFICILE;
    recette.setDifficulte(diffAttendue);
    String instructionsAttendue = "les instructions...";
    recette.setInstructions(instructionsAttendue);
    int nbPersonnesAttendu = 4;
    recette.setNbPersonnes(nbPersonnesAttendu);
    String sourceAttendue = "www.dummy.com";
    recette.setSource(sourceAttendue);
    Commentaire comm = new Commentaire();
    Long idCommAttendu = 3L;
    comm.setId(idCommAttendu);
    recette.setCommentaire(comm);
    Categorie cat1 = new Categorie();
    Categorie cat2 = new Categorie();
    Long idCatAttendu = 5L;
    cat2.setId(idCatAttendu);
    recette.getCategories().add(cat1);
    recette.getCategories().add(cat2);
    Ingredient ing1 = new Ingredient();
    Ingredient ing2 = new Ingredient();
    Long idIngAttendu = 7L;
    ing2.setId(idIngAttendu);
    recette.getIngredients().add(ing1);
    recette.getIngredients().add(ing2);

    // When

    RecetteCommandObj command = converter.convert(recette);

    // Then

    assertEquals(idAttendue, command.getId(), "L'id ne correspond pas");
    assertEquals(tempsPrepAttendu, command.getTempsPrep(), "Le tempsPrep ne correspond pas");
    assertEquals(tempsCuissonAttendu, command.getTempsCuisson(),
        "Le tempsCuisson ne correspond pas");
    assertEquals(descAttendue, command.getDescription(), "La description ne correspond pas");
    assertEquals(diffAttendue, command.getDifficulte(), "La difficulte ne correspond pas");
    assertEquals(instructionsAttendue, command.getInstructions(),
        "Les instructions ne correspondent pas");
    assertEquals(nbPersonnesAttendu, command.getNbPersonnes(),
        "Le nombre de personnes ne correspond pas");
    assertEquals(sourceAttendue, command.getSource(), "La source ne correspond pas");
    assertEquals(idCommAttendu, command.getCommentaire().getId(),
        "L'id commentaire ne correspond pas");
    assertEquals(2, command.getCategories().size(), "Il devrait y avoir 2 categories");
    assertTrue(command.getCategories().stream().anyMatch(cat -> cat.getId() == idCatAttendu),
        "L'id de la catégorie est introuvable");
    assertEquals(2, command.getIngredients().size(), "Il devrait y avoir 2 ingrédients");
    assertTrue(command.getIngredients().stream().anyMatch(ing -> ing.getId() == idIngAttendu),
        "L'id de l'ingrédient est introuvable");
  }
}
