package fr.pgah.valarep.spring.blahmiton.commandobj;

import java.util.HashSet;
import java.util.Set;
import fr.pgah.valarep.spring.blahmiton.model.Difficulte;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecetteCommandObj {

  private Long id;
  private String description;
  private int tempsPrep;
  private int tempsCuisson;
  private int nbPersonnes;
  private String source;
  private String instructions;
  private Difficulte difficulte;
  private CommentaireCommandObj commentaire = new CommentaireCommandObj();
  private Set<IngredientCommandObj> ingredients = new HashSet<>();
  private Set<CategorieCommandObj> categories = new HashSet<>();
}
