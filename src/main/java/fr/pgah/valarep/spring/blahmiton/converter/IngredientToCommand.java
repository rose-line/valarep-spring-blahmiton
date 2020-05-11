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
public class IngredientToCommand implements Converter<Ingredient, IngredientCommandObj> {

  // METTRE @NotNull pour faire merder les tests
  @NonNull
  private UniteDeMesureToCommand udmConverter;

  @Nullable
  @Override
  public IngredientCommandObj convert(Ingredient source) {

    if (source == null) {
      return null;
    }

    IngredientCommandObj ingredientCommandObj = new IngredientCommandObj();
    ingredientCommandObj.setId(source.getId());
    ingredientCommandObj.setNom(source.getNom());
    ingredientCommandObj.setQuantite(source.getQuantite());
    ingredientCommandObj.setUniteDeMesure(udmConverter.convert(source.getUniteDeMesure()));

    return ingredientCommandObj;
  }
}
