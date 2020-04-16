package fr.pgah.valarep.spring.blahmiton.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import fr.pgah.valarep.spring.blahmiton.model.Recette;
import fr.pgah.valarep.spring.blahmiton.repo.RecetteRepo;

public class ServiceRecettesImplTests {

  @Mock
  RecetteRepo repoMock;

  @Test
  void avecUneRecette_getRecette_retourneExactementUneRecette() {

    Recette recette = new Recette();
    Set<Recette> recettes = new HashSet<>();
    recettes.add(recette);

    MockitoAnnotations.initMocks(this);
    when(repoMock.findAll()).thenReturn(recettes);

    ServiceRecettesImpl service = new ServiceRecettesImpl(repoMock);

    Set<Recette> recettesRecues = service.getRecettes();

    assertEquals(1, recettesRecues.size(), "Devrait y avoir 1 recette");
    verify(repoMock, times(1)).findAll();
  }
}
