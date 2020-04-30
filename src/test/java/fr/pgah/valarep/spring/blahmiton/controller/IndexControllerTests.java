package fr.pgah.valarep.spring.blahmiton.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import fr.pgah.valarep.spring.blahmiton.model.Recette;
import fr.pgah.valarep.spring.blahmiton.service.ServiceRecettes;

@SpringBootTest
@AutoConfigureMockMvc
public class IndexControllerTests {

  @Captor
  ArgumentCaptor<Set<Recette>> recettesCaptor;

  @Mock
  ServiceRecettes serviceRecetteMock;

  @Mock
  Model modelMock;

  @Autowired
  private MockMvc mockMVC;

  @Test
  void routage_surRacine_devraitRetournerBonneVue() throws Exception {

    String vueAttendue = "index";

    mockMVC.perform(get("/")).andExpect(status().isOk()).andExpect(view().name(vueAttendue));
  }

  @Test
  void getIndex_devraitRetournerBonneVue() {

    Set<Recette> recettes = new HashSet<>();
    recettes.add(new Recette());
    recettes.add(new Recette());
    recettes.add(new Recette());

    MockitoAnnotations.initMocks(this);
    when(serviceRecetteMock.getRecettes()).thenReturn(recettes);

    IndexController controller = new IndexController(serviceRecetteMock);
    String vueAttendue = "index";
    String vueEffective = controller.getIndex(modelMock);

    assertEquals(vueAttendue, vueEffective, "Ne retourne pas la bonne vue");
    verify(serviceRecetteMock, times(1)).getRecettes();
    verify(modelMock, times(1)).addAttribute(eq("recettes"), recettesCaptor.capture());
    assertEquals(3, recettesCaptor.getValue().size(),
        "Pas le bon nombre de recettes passées dans le Model");
  }
}
