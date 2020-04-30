package fr.pgah.valarep.spring.blahmiton.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Optional;
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

  @Test
  void avecUneRecette_findById_retourneLaRecette() {

    Recette recette = new Recette();
    recette.setId(1L);

    MockitoAnnotations.initMocks(this);
    when(repoMock.findById(anyLong())).thenReturn(Optional.of(recette));

    ServiceRecettesImpl service = new ServiceRecettesImpl(repoMock);
    Recette recetteEffective = service.findById(1L);

    assertNotNull(recetteEffective, "Recette effective est null");
    verify(repoMock, times(1)).findById(anyLong());
    verify(repoMock, never()).findAll();
  }
}
