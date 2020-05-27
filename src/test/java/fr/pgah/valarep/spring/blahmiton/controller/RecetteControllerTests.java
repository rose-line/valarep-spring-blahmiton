package fr.pgah.valarep.spring.blahmiton.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import fr.pgah.valarep.spring.blahmiton.commandobj.RecetteCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Recette;
import fr.pgah.valarep.spring.blahmiton.service.ServiceRecettes;

@SpringBootTest
@AutoConfigureMockMvc
public class RecetteControllerTests {

  @Autowired
  private MockMvc mockMVC;

  @MockBean
  private ServiceRecettes service;

  @Test
  void surId1_devraitRetournerBonneVue() throws Exception {

    Recette recette = new Recette();
    Long idAttendu = 1L;
    recette.setId(idAttendu);

    MockitoAnnotations.initMocks(service);
    when(service.findById(anyLong())).thenReturn(recette);
    String vueAttendue = "recette/voir";

    mockMVC.perform(get("/recette/voir/" + idAttendu)).andExpect(status().isOk())
        .andExpect(view().name(vueAttendue))
        .andExpect(model().attributeExists("recette"));
  }

  @Test
  void testGlobal_posterNouvelleRecette() throws Exception {

    RecetteCommandObj cmd = new RecetteCommandObj();
    Long idAttendue = 19L;
    cmd.setId(idAttendue);
    String vueAttendue = "redirect:/recette/voir/" + idAttendue;

    MockitoAnnotations.initMocks(service);
    when(service.saveRecetteCommandObj(any())).thenReturn(cmd);

    // Je teste en POST car c'est ce qui se passe quand j'appuie sur
    // le submit du formulaire
    mockMVC.perform(post("/recette")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id", "")
        .param("description", "une description"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name(vueAttendue));
  }

  @Test
  void demandeModificationRecette_devraitPeuplerETRetournerBonneVue() throws Exception {

    Long idAttendu = 1L;
    RecetteCommandObj cmd = new RecetteCommandObj();
    cmd.setId(idAttendu);

    MockitoAnnotations.initMocks(service);
    when(service.findCommandById(anyLong())).thenReturn(cmd);

    // pour modifier, je suis un link : c'est un get
    mockMVC.perform(get("/recette/modifier/" + idAttendu))
        .andExpect(status().isOk())
        .andExpect(view().name("recette/recetteform"))
        .andExpect(model().attributeExists("recette"));
  }

  @Test
  void demanderSuppressionRecette_devraitRelayerAuService() throws Exception {

    Long idASupprimer = 20L;

    mockMVC.perform(get("/recette/supprimer/" + idASupprimer))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/"));

    verify(service, times(1)).deleteById(idASupprimer);
  }
}
