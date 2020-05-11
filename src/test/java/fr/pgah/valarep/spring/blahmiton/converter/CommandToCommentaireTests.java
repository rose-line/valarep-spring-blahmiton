package fr.pgah.valarep.spring.blahmiton.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import fr.pgah.valarep.spring.blahmiton.commandobj.CommentaireCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Commentaire;

public class CommandToCommentaireTests {

  @Test
  public void convertirNull_DevraitRetournerNull() throws Exception {
    CommandToCommentaire converter = new CommandToCommentaire();

    Commentaire comm = converter.convert(null);

    assertNull(comm, "La Commentaire devrait être null");
  }

  @Test
  public void convertirCommandVide_DevraitRetournerCommentaireVide() throws Exception {
    CommandToCommentaire converter = new CommandToCommentaire();

    Commentaire comm = converter.convert(new CommentaireCommandObj());

    assertNotNull(comm, "La Commentaire ne devrait pas être null");
  }

  @Test
  public void convertirCommandPeuplee_DevraitRetournerCommentairePeuple() throws Exception {

    CommandToCommentaire converter = new CommandToCommentaire();
    CommentaireCommandObj command = new CommentaireCommandObj();
    Long idAttendue = 1L;
    command.setId(idAttendue);
    String commAttendu = "Un Nom";
    command.setCommentaire(commAttendu);

    Commentaire comm = converter.convert(command);

    assertEquals(idAttendue, comm.getId(), "L'id ne correspond pas");
    assertEquals(commAttendu, comm.getCommentaire(), "Le commentaire ne correspond pas");
  }
}
