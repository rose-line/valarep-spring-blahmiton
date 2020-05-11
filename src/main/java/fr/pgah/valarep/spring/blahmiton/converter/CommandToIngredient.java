package fr.pgah.valarep.spring.blahmiton.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import fr.pgah.valarep.spring.blahmiton.commandobj.IngredientCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Ingredient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CommandToIngredient implements Converter<IngredientCommandObj, Ingredient> {

  @NonNull
  private CommandToUniteDeMesure udmConverter;

  @Nullable
  @Override
  public Ingredient convert(IngredientCommandObj source) {

    if (source == null) {
      return null;
    }

    final Ingredient ingredient = new Ingredient();
    ingredient.setId(source.getId());
    ingredient.setNom(source.getNom());
    ingredient.setQuantite(source.getQuantite());
    ingredient.setUniteDeMesure(udmConverter.convert(source.getUniteDeMesure()));

    return ingredient;
  }
}
