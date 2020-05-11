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
public class RecetteToCommand implements Converter<Recette, RecetteCommandObj> {

  private CommentaireToCommand commConverter;
  private CategorieToCommand catConverter;
  private IngredientToCommand ingConverter;

  @Nullable
  @Override
  public RecetteCommandObj convert(Recette source) {

    if (source == null) {
      return null;
    }

    final RecetteCommandObj recetteCommandObj = new RecetteCommandObj();
    recetteCommandObj.setId(source.getId());
    recetteCommandObj.setDescription(source.getDescription());
    recetteCommandObj.setTempsPrep(source.getTempsPrep());
    recetteCommandObj.setTempsCuisson(source.getTempsCuisson());
    recetteCommandObj.setNbPersonnes(source.getNbPersonnes());
    recetteCommandObj.setDifficulte(source.getDifficulte());
    recetteCommandObj.setSource(source.getSource());
    recetteCommandObj.setInstructions(source.getInstructions());
    recetteCommandObj.setCommentaire(commConverter.convert(source.getCommentaire()));

    for (Categorie cat : source.getCategories()) {
      CategorieCommandObj catCommand = catConverter.convert(cat);
      recetteCommandObj.getCategories().add(catCommand);
    }

    for (Ingredient ing : source.getIngredients()) {
      IngredientCommandObj ingCommand = ingConverter.convert(ing);
      recetteCommandObj.getIngredients().add(ingCommand);
    }

    return recetteCommandObj;
  }
}
