package fr.pgah.valarep.spring.blahmiton.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import fr.pgah.valarep.spring.blahmiton.commandobj.CategorieCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Categorie;

@Component
public class CategorieToCommand implements Converter<Categorie, CategorieCommandObj> {

  @Nullable
  @Override
  public CategorieCommandObj convert(Categorie source) {

    if (source == null) {
      return null;
    }

    CategorieCommandObj categorieCommandObj = new CategorieCommandObj();
    categorieCommandObj.setId(source.getId());
    categorieCommandObj.setNom(source.getNom());

    return categorieCommandObj;
  }
}
