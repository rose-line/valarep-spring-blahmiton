package fr.pgah.valarep.spring.blahmiton.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import fr.pgah.valarep.spring.blahmiton.commandobj.IngredientCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Ingredient;
import fr.pgah.valarep.spring.blahmiton.model.UniteDeMesure;

public class IngredientToCommandTests {

  @Test
  public void convertirNull_DevraitRetournerNull() throws Exception {
    IngredientToCommand converter = new IngredientToCommand(new UniteDeMesureToCommand());

    IngredientCommandObj command = converter.convert(null);

    assertNull(command, "Le CommandObj devrait être null");
  }

  @Test
  public void convertirIngredientVide_DevraitRetournerCommandVide() throws Exception {
    IngredientToCommand converter = new IngredientToCommand(new UniteDeMesureToCommand());

    IngredientCommandObj command = converter.convert(new Ingredient());

    assertNotNull(command, "Le CommandObj ne devrait pas être null");
  }

  @Test
  public void convertirIngredientPeuple_DevraitRetournerCommandPeuple() throws Exception {

    IngredientToCommand converter = new IngredientToCommand(new UniteDeMesureToCommand());
    Ingredient ing = new Ingredient();
    Long idAttendue = 1L;
    ing.setId(idAttendue);
    String nomAttendu = "Un Nom";
    ing.setNom(nomAttendu);
    Double qteAttendue = 20d;
    ing.setQuantite(qteAttendue);
    UniteDeMesure udmAttendue = new UniteDeMesure();
    udmAttendue.setId(2L);
    udmAttendue.setNom("unite");

    IngredientCommandObj command = converter.convert(ing);

    assertEquals(idAttendue, command.getId(), "L'id ne correspond pas");
    assertEquals(nomAttendu, command.getNom(), "Le nom ne correspond pas");
  }
}
