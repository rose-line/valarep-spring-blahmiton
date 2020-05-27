package fr.pgah.valarep.spring.blahmiton.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import fr.pgah.valarep.spring.blahmiton.commandobj.RecetteCommandObj;
import fr.pgah.valarep.spring.blahmiton.controller.RecetteController;
import fr.pgah.valarep.spring.blahmiton.converter.CommandToRecette;
import fr.pgah.valarep.spring.blahmiton.converter.RecetteToCommand;
import fr.pgah.valarep.spring.blahmiton.model.Recette;
import fr.pgah.valarep.spring.blahmiton.repo.RecetteRepo;

public class ServiceRecettesImplTests {

  @Mock
  RecetteRepo repoMock;

  @Mock
  RecetteToCommand recetteToCmdMock;

  @Mock
  CommandToRecette cmdToRecetteMock;

  @Test
  void avecUneRecette_getRecette_retourneExactementUneRecette() {

    Recette recette = new Recette();
    Set<Recette> recettes = new HashSet<>();
    recettes.add(recette);

    MockitoAnnotations.initMocks(this);
    when(repoMock.findAll()).thenReturn(recettes);

    ServiceRecettesImpl service =
        new ServiceRecettesImpl(repoMock, cmdToRecetteMock, recetteToCmdMock);

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

    ServiceRecettesImpl service =
        new ServiceRecettesImpl(repoMock, cmdToRecetteMock, recetteToCmdMock);
    Recette recetteEffective = service.findById(1L);

    assertNotNull(recetteEffective, "Recette effective est null");
    verify(repoMock, times(1)).findById(anyLong());
    verify(repoMock, never()).findAll();
  }

  @Test
  void avecUneRecette_findCommandById_devraitRetournerLeCOmmandObj() {

    // Given
    Long idAttendu = 1L;

    Recette recette = new Recette();
    recette.setId(idAttendu);

    RecetteCommandObj cmd = new RecetteCommandObj();
    cmd.setId(idAttendu);

    MockitoAnnotations.initMocks(this);
    when(recetteToCmdMock.convert(any())).thenReturn(cmd);
    when(repoMock.findById(anyLong())).thenReturn(Optional.of(recette));

    ServiceRecettesImpl service =
        new ServiceRecettesImpl(repoMock, cmdToRecetteMock, recetteToCmdMock);

    // When

    RecetteCommandObj cmdRetournee = service.findCommandById(idAttendu);

    // Then

    assertNotNull(cmdRetournee, "Le CommandObj retourne ne devrait pas être null");
    verify(repoMock, times(1)).findById(anyLong());
    verify(repoMock, never()).findAll();
  }

  @Test
  void sansRecette_findCommandById_devraitRetournerNull() {
    // Given

    Long idAttendu = 100L;
    RecetteCommandObj cmd = new RecetteCommandObj();
    cmd.setId(idAttendu);

    MockitoAnnotations.initMocks(this);
    when(recetteToCmdMock.convert(any())).thenReturn(cmd);

    ServiceRecettesImpl service =
        new ServiceRecettesImpl(repoMock, cmdToRecetteMock, recetteToCmdMock);

    // When - Then

    assertThrows(NoSuchElementException.class, () -> {
      service.findCommandById(idAttendu);
    });
  }

  @Test
  void avecUneRecette_deleteById_devraitRelayerAuRepo() {

    // Given

    Long idRecetteASupprimer = 20L;

    MockitoAnnotations.initMocks(this);

    ServiceRecettesImpl service =
        new ServiceRecettesImpl(repoMock, cmdToRecetteMock, recetteToCmdMock);

    // When

    service.deleteById(idRecetteASupprimer);

    // Then

    verify(repoMock, times(1)).deleteById(idRecetteASupprimer);
  }
}
