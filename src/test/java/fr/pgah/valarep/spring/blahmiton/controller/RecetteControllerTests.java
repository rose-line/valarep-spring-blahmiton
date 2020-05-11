package fr.pgah.valarep.spring.blahmiton.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import fr.pgah.valarep.spring.blahmiton.model.Recette;
import fr.pgah.valarep.spring.blahmiton.service.ServiceRecettes;

@SpringBootTest
@AutoConfigureMockMvc
public class RecetteControllerTests {

  @Autowired
  private MockMvc mockMVC;

  @Mock
  private ServiceRecettes service;

  @Test
  void surId1_devraitRetournerBonneVue() throws Exception {

    Recette recette = new Recette();
    recette.setId(1L);

    MockitoAnnotations.initMocks(service);
    when(service.findById(anyLong())).thenReturn(recette);
    String vueAttendue = "recette/voir";

    mockMVC.perform(get("/recette/voir/1")).andExpect(status().isOk())
        .andExpect(view().name(vueAttendue));
  }

}
