package fr.pgah.valarep.spring.blahmiton.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import fr.pgah.valarep.spring.blahmiton.commandobj.CategorieCommandObj;
import fr.pgah.valarep.spring.blahmiton.commandobj.IngredientCommandObj;
import fr.pgah.valarep.spring.blahmiton.commandobj.RecetteCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Categorie;
import fr.pgah.valarep.spring.blahmiton.model.Ingredient;
import fr.pgah.valarep.spring.blahmiton.model.Recette;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CommandToRecette implements Converter<RecetteCommandObj, Recette> {

  private CommandToCommentaire commConverter;
  private CommandToCategorie catConverter;
  private CommandToIngredient ingConverter;

  @Nullable
  @Override
  public Recette convert(RecetteCommandObj source) {

    if (source == null) {
      return null;
    }

    final Recette recette = new Recette();
    recette.setId(source.getId());
    recette.setDescription(source.getDescription());
    recette.setTempsPrep(source.getTempsPrep());
    recette.setTempsCuisson(source.getTempsCuisson());
    recette.setNbPersonnes(source.getNbPersonnes());
    recette.setDifficulte(source.getDifficulte());
    recette.setSource(source.getSource());
    recette.setInstructions(source.getInstructions());
    recette.setCommentaire(commConverter.convert(source.getCommentaire()));

    for (CategorieCommandObj commandCat : source.getCategories()) {
      Categorie cat = catConverter.convert(commandCat);
      recette.getCategories().add(cat);
    }

    for (IngredientCommandObj commandIng : source.getIngredients()) {
      Ingredient ing = ingConverter.convert(commandIng);
      recette.getIngredients().add(ing);
    }

    return recette;
  }
}
