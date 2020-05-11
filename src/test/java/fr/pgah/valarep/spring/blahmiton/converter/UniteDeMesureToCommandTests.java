package fr.pgah.valarep.spring.blahmiton.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import fr.pgah.valarep.spring.blahmiton.commandobj.UniteDeMesureCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.UniteDeMesure;

public class UniteDeMesureToCommandTests {

  @Test
  public void convertirNull_DevraitRetournerNull() throws Exception {
    UniteDeMesureToCommand converter = new UniteDeMesureToCommand();

    UniteDeMesureCommandObj command = converter.convert(null);

    assertNull(command, "Le CommandObj devrait être null");
  }

  @Test
  public void convertirUniteDeMesureVide_DevraitRetournerCommandVide() throws Exception {
    UniteDeMesureToCommand converter = new UniteDeMesureToCommand();

    UniteDeMesureCommandObj command = converter.convert(new UniteDeMesure());

    assertNotNull(command, "Le CommandObj ne devrait pas être null");
  }

  @Test
  public void convertirUniteDeMesurePeuplee_DevraitRetournerCommandPeuplee() throws Exception {

    UniteDeMesureToCommand converter = new UniteDeMesureToCommand();
    UniteDeMesure udm = new UniteDeMesure();
    Long idAttendue = 1L;
    udm.setId(idAttendue);
    String nomAttendu = "Un Nom";
    udm.setNom(nomAttendu);

    UniteDeMesureCommandObj command = converter.convert(udm);

    assertEquals(idAttendue, command.getId(), "L'id ne correspond pas");
    assertEquals(nomAttendu, command.getNom(), "Le nom ne correspond pas");
  }
}
