package fr.pgah.valarep.spring.blahmiton.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import fr.pgah.valarep.spring.blahmiton.model.Recette;
import fr.pgah.valarep.spring.blahmiton.service.ServiceRecettes;

public class IndexControllerTests {

  @Mock
  ServiceRecettes serviceRecetteMock;

  @Mock
  Model modelMock;

  @Test
  void getIndex_devraitRetournerBonneVue() {

    Recette recette = new Recette();
    Set<Recette> recettes = new HashSet<>();
    recettes.add(recette);

    MockitoAnnotations.initMocks(this);
    when(serviceRecetteMock.getRecettes()).thenReturn(recettes);

    IndexController controller = new IndexController(serviceRecetteMock);
    String vueAttendue = "index";
    String vueEffective = controller.getIndex(modelMock);

    assertEquals(vueAttendue, vueEffective, "Ne retourne pas la bonne vue");
    verify(serviceRecetteMock, times(1)).getRecettes();
    verify(modelMock, times(1)).addAttribute(eq("recettes"), anySet());
  }
}
