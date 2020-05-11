package fr.pgah.valarep.spring.blahmiton.commandobj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientCommandObj {

  private Long id;
  private String nom;
  private Double quantite;
  private UniteDeMesureCommandObj uniteDeMesure;
}
