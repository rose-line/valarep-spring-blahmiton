package fr.pgah.valarep.spring.blahmiton.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import fr.pgah.valarep.spring.blahmiton.commandobj.CategorieCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Categorie;

@Component
public class CommandToCategorie implements Converter<CategorieCommandObj, Categorie> {

  @Nullable
  @Override
  public Categorie convert(CategorieCommandObj source) {

    if (source == null) {
      return null;
    }

    Categorie cat = new Categorie();
    cat.setId(source.getId());
    cat.setNom(source.getNom());

    return cat;
  }
}
