package fr.pgah.valarep.spring.blahmiton.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import fr.pgah.valarep.spring.blahmiton.commandobj.UniteDeMesureCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.UniteDeMesure;

public class CommandToUniteDeMesureTests {

  @Test
  public void convertirNull_DevraitRetournerNull() throws Exception {
    CommandToUniteDeMesure converter = new CommandToUniteDeMesure();

    UniteDeMesure udm = converter.convert(null);

    assertNull(udm, "L'UniteDeMesure devrait être null");
  }

  @Test
  public void convertirCommandVide_DevraitRetournerUniteDeMesureVide() throws Exception {
    CommandToUniteDeMesure converter = new CommandToUniteDeMesure();

    UniteDeMesure udm = converter.convert(new UniteDeMesureCommandObj());

    assertNotNull(udm, "L'UniteDeMesure ne devrait pas être null");
  }

  @Test
  public void convertirCommandPeuplee_DevraitRetournerUniteDeMesurePeuplee() throws Exception {

    CommandToUniteDeMesure converter = new CommandToUniteDeMesure();
    UniteDeMesureCommandObj command = new UniteDeMesureCommandObj();
    Long idAttendue = 1L;
    command.setId(idAttendue);
    String nomAttendu = "Un Nom";
    command.setNom(nomAttendu);

    UniteDeMesure udm = converter.convert(command);

    assertEquals(idAttendue, udm.getId(), "L'id ne correspond pas");
    assertEquals(nomAttendu, udm.getNom(), "Le nom ne correspond pas");
  }
}
