package fr.pgah.valarep.spring.blahmiton.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import fr.pgah.valarep.spring.blahmiton.commandobj.UniteDeMesureCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.UniteDeMesure;

@Component
public class UniteDeMesureToCommand implements Converter<UniteDeMesure, UniteDeMesureCommandObj> {

  @Nullable
  @Override
  public UniteDeMesureCommandObj convert(UniteDeMesure source) {

    if (source == null) {
      return null;
    }

    final UniteDeMesureCommandObj udmCommandObj = new UniteDeMesureCommandObj();
    udmCommandObj.setId(source.getId());
    udmCommandObj.setNom(source.getNom());

    return udmCommandObj;
  }
}
