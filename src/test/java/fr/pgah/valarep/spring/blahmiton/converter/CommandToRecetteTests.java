package fr.pgah.valarep.spring.blahmiton.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import fr.pgah.valarep.spring.blahmiton.commandobj.CategorieCommandObj;
import fr.pgah.valarep.spring.blahmiton.commandobj.CommentaireCommandObj;
import fr.pgah.valarep.spring.blahmiton.commandobj.IngredientCommandObj;
import fr.pgah.valarep.spring.blahmiton.commandobj.RecetteCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Difficulte;
import fr.pgah.valarep.spring.blahmiton.model.Recette;

public class CommandToRecetteTests {

  @Test
  public void convertirNull_DevraitRetournerNull() throws Exception {
    CommandToRecette converter = new CommandToRecette(
        new CommandToCommentaire(),
        new CommandToCategorie(),
        new CommandToIngredient(new CommandToUniteDeMesure()));

    Recette recette = converter.convert(null);

    assertNull(recette, "La Recette devrait être null");
  }

  @Test
  public void convertirCommandVide_DevraitRetournerRecetteVide() throws Exception {
    CommandToRecette converter = new CommandToRecette(
        new CommandToCommentaire(),
        new CommandToCategorie(),
        new CommandToIngredient(new CommandToUniteDeMesure()));

    Recette recette = converter.convert(new RecetteCommandObj());

    assertNotNull(recette, "La Recette ne devrait pas être null");
  }

  @Test
  public void convertirCommandPeuplee_DevraitRetournerRecettePeuplee() throws Exception {

    CommandToRecette converter = new CommandToRecette(
        new CommandToCommentaire(),
        new CommandToCategorie(),
        new CommandToIngredient(new CommandToUniteDeMesure()));

    RecetteCommandObj command = new RecetteCommandObj();
    Long idAttendue = 1L;
    command.setId(idAttendue);
    String descAttendue = "La recette";
    command.setDescription(descAttendue);
    int tempsPrepAttendu = 15;
    command.setTempsPrep(tempsPrepAttendu);
    int tempsCuissonAttendu = 30;
    command.setTempsCuisson(tempsCuissonAttendu);
    Difficulte diffAttendue = Difficulte.DIFFICILE;
    command.setDifficulte(diffAttendue);
    String instructionsAttendue = "les instructions...";
    command.setInstructions(instructionsAttendue);
    int nbPersonnesAttendu = 4;
    command.setNbPersonnes(nbPersonnesAttendu);
    String sourceAttendue = "www.dummy.com";
    command.setSource(sourceAttendue);
    CommentaireCommandObj commCommand = new CommentaireCommandObj();
    Long idCommAttendu = 3L;
    commCommand.setId(idCommAttendu);
    command.setCommentaire(commCommand);
    CategorieCommandObj catCommand1 = new CategorieCommandObj();
    Long idCatAttendu = 4L;
    catCommand1.setId(idCatAttendu);
    CategorieCommandObj catCommand2 = new CategorieCommandObj();
    command.getCategories().add(catCommand1);
    command.getCategories().add(catCommand2);
    IngredientCommandObj ingCommand1 = new IngredientCommandObj();
    Long idIngAttendu = 9L;
    ingCommand1.setId(idIngAttendu);
    IngredientCommandObj ingCommand2 = new IngredientCommandObj();
    command.getIngredients().add(ingCommand1);
    command.getIngredients().add(ingCommand2);

    Recette recette = converter.convert(command);

    assertEquals(idAttendue, recette.getId(), "L'id ne correspond pas");
    assertEquals(tempsPrepAttendu, recette.getTempsPrep(), "Le tempsPrep ne correspond pas");
    assertEquals(tempsCuissonAttendu, recette.getTempsCuisson(),
        "Le tempsCuisson ne correspond pas");
    assertEquals(descAttendue, recette.getDescription(), "La description ne correspond pas");
    assertEquals(diffAttendue, recette.getDifficulte(), "La difficulte ne correspond pas");
    assertEquals(instructionsAttendue, recette.getInstructions(),
        "Les instructions ne correspondent pas");
    assertEquals(nbPersonnesAttendu, recette.getNbPersonnes(),
        "Le nombre de personnes ne correspond pas");
    assertEquals(sourceAttendue, recette.getSource(), "La source ne correspond pas");
    assertEquals(idCommAttendu, recette.getCommentaire().getId(),
        "L'id commentaire ne correspond pas");
    assertEquals(2, recette.getCategories().size(), "Il devrait y avoir 2 categories");
    assertTrue(recette.getCategories().stream().anyMatch(cat -> cat.getId() == idCatAttendu),
        "L'id de la catégorie est introuvable");
    assertEquals(2, recette.getIngredients().size(), "Il devrait y avoir 2 ingrédients");
    assertTrue(recette.getIngredients().stream().anyMatch(ing -> ing.getId() == idIngAttendu),
        "L'id de l'ingrédient est introuvable");
  }
}
