package fr.pgah.valarep.spring.blahmiton.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import fr.pgah.valarep.spring.blahmiton.commandobj.CategorieCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Categorie;

public class CommandToCategorieTests {

  @Test
  public void convertirNull_DevraitRetournerNull() throws Exception {
    CommandToCategorie converter = new CommandToCategorie();

    Categorie cat = converter.convert(null);

    assertNull(cat, "La categorie devrait être null");
  }

  @Test
  public void convertirCommandVide_DevraitRetournerCategorieVide() throws Exception {
    CommandToCategorie converter = new CommandToCategorie();

    Categorie cat = converter.convert(new CategorieCommandObj());

    assertNotNull(cat, "La categorie ne devrait pas être null");
  }

  @Test
  public void convertirCommandPeuplee_DevraitRetournerCategoriePeuplee() throws Exception {

    // Given

    CommandToCategorie converter = new CommandToCategorie();
    CategorieCommandObj command = new CategorieCommandObj();
    Long idAttendue = 1L;
    command.setId(idAttendue);
    String nomAttendu = "Un Nom";
    command.setNom(nomAttendu);

    // When

    Categorie cat = converter.convert(command);

    // Then

    assertEquals(idAttendue, cat.getId(), "L'id ne correspond pas");
    assertEquals(nomAttendu, cat.getNom(), "Le nom ne correspond pas");
  }
}
