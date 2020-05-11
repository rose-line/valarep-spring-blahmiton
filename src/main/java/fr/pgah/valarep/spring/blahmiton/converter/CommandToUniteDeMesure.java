package fr.pgah.valarep.spring.blahmiton.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import fr.pgah.valarep.spring.blahmiton.commandobj.UniteDeMesureCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.UniteDeMesure;

@Component
public class CommandToUniteDeMesure implements Converter<UniteDeMesureCommandObj, UniteDeMesure> {

  @Nullable
  @Override
  public UniteDeMesure convert(UniteDeMesureCommandObj source) {

    if (source == null) {
      return null;
    }

    final UniteDeMesure udm = new UniteDeMesure();
    udm.setId(source.getId());
    udm.setNom(source.getNom());

    return udm;
  }
}
