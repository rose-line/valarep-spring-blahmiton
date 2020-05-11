package fr.pgah.valarep.spring.blahmiton.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import fr.pgah.valarep.spring.blahmiton.commandobj.CategorieCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Categorie;

public class CategorieToCommandTests {

  @Test
  public void convertirNull_DevraitRetournerNull() throws Exception {
    CategorieToCommand converter = new CategorieToCommand();

    CategorieCommandObj command = converter.convert(null);

    assertNull(command, "Le CommandObj devrait être null");
  }

  @Test
  public void convertirCategorieVide_DevraitRetournerCommandVide() throws Exception {
    CategorieToCommand converter = new CategorieToCommand();

    CategorieCommandObj command = converter.convert(new Categorie());

    assertNotNull(command, "Le CommandObj ne devrait pas être null");
  }

  @Test
  public void convertirCategoriePeuplee_DevraitRetournerCommandPeuplee() throws Exception {

    // Given (étant donné...) (setup, installation : instanciation et initialisation de ou des
    // objet(s) à tester, configuration des mocks...)

    CategorieToCommand converter = new CategorieToCommand();
    Categorie cat = new Categorie();
    Long idAttendue = 1L;
    cat.setId(idAttendue);
    String nomAttendu = "Un Nom";
    cat.setNom(nomAttendu);

    // When (quand je...) (action, unité à tester)

    CategorieCommandObj command = converter.convert(cat);

    // Then (alors il faut...) (vérification)

    assertEquals(idAttendue, command.getId(), "L'id ne correspond pas");
    assertEquals(nomAttendu, command.getNom(), "Le nom ne correspond pas");
  }
}
