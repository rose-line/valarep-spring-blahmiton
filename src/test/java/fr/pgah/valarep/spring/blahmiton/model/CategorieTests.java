package fr.pgah.valarep.spring.blahmiton.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CategorieTests {

  @Test
  void getters_Setters_Nom() {

    String nomAttendu = "Dessert";
    Categorie cat = new Categorie();
    cat.setNom(nomAttendu);

    assertEquals(nomAttendu, cat.getNom());
  }

}
