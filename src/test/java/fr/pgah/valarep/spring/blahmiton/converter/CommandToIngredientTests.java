package fr.pgah.valarep.spring.blahmiton.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import fr.pgah.valarep.spring.blahmiton.commandobj.IngredientCommandObj;
import fr.pgah.valarep.spring.blahmiton.commandobj.UniteDeMesureCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Ingredient;

public class CommandToIngredientTests {

  @Test
  public void convertirNull_DevraitRetournerNull() throws Exception {
    CommandToIngredient converter = new CommandToIngredient(new CommandToUniteDeMesure());

    Ingredient ing = converter.convert(null);

    assertNull(ing, "L'Ingredient devrait être null");
  }

  @Test
  public void convertirCommandVide_DevraitRetournerIngredientVide() throws Exception {
    CommandToIngredient converter = new CommandToIngredient(new CommandToUniteDeMesure());

    Ingredient ing = converter.convert(new IngredientCommandObj());

    assertNotNull(ing, "L'Ingredient ne devrait pas être null");
  }

  @Test
  public void convertirCommandPeuplee_DevraitRetournerIngredientPeuplee() throws Exception {

    CommandToIngredient converter = new CommandToIngredient(new CommandToUniteDeMesure());
    IngredientCommandObj command = new IngredientCommandObj();
    Long idAttendue = 1L;
    command.setId(idAttendue);
    String nomAttendu = "Un Nom";
    command.setNom(nomAttendu);
    Double qteAttendue = 10d;
    command.setQuantite(qteAttendue);
    UniteDeMesureCommandObj udmCommandAttendue = new UniteDeMesureCommandObj();
    Long idUdmAttendue = 2L;
    udmCommandAttendue.setId(idUdmAttendue);
    String nomUdmAttendue = "udm";
    udmCommandAttendue.setNom(nomUdmAttendue);
    command.setUniteDeMesure(udmCommandAttendue);

    Ingredient ing = converter.convert(command);

    assertEquals(idAttendue, ing.getId(), "L'id ne correspond pas");
    assertEquals(nomAttendu, ing.getNom(), "Le nom ne correspond pas");
    assertEquals(qteAttendue, ing.getQuantite(), "La quantité ne correspond pas");
    assertNotNull(ing.getUniteDeMesure(), "L'UDM ne devrait pas être null");
    assertEquals(idUdmAttendue, ing.getUniteDeMesure().getId(), "L'id de l'udm ne correspond pas");
    assertEquals(nomUdmAttendue, ing.getUniteDeMesure().getNom(),
        "Le nom de l'udm ne correspond pas");
  }
}
