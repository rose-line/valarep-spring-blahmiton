package fr.pgah.valarep.spring.blahmiton.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import fr.pgah.valarep.spring.blahmiton.repo.UniteDeMesureRepo;

@DataJpaTest
public class UniteDeMesureTests {

  @Autowired
  private UniteDeMesureRepo repo;

  @Test
  void requetePourCAC_retourneCAC() {

    String nomAttendu = "cac";

    // List<UniteDeMesure> udms = repo.findByNom("cac");
    // UniteDeMesure udm = udms.get(0);
    // String nomEffectif = udm.getNom();

    String nomEffectif = repo.findByNom("cac").get(0).getNom();

    assertEquals(nomAttendu, nomEffectif, "Les noms ne correspondent pas");
  }

  @Test
  void requetePourCAS_retourneCAS() {

    String nomAttendu = "cas";

    String nomEffectif = repo.findByNom("cas").get(0).getNom();

    assertEquals(nomAttendu, nomEffectif, "Les noms ne correspondent pas");
  }

  @ParameterizedTest
  @ValueSource(strings = {"g", "l", "unite"})
  void requetePourAutreUDM_retourneBonnesUDMs(String nomUDMAttendu) {

    String nomEffectif = repo.findByNom(nomUDMAttendu).get(0).getNom();

    assertEquals(nomUDMAttendu, nomEffectif, "Les noms ne correspondent pas");
  }
}
