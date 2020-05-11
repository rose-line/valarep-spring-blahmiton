package fr.pgah.valarep.spring.blahmiton.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import fr.pgah.valarep.spring.blahmiton.commandobj.CommentaireCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Commentaire;

public class CommentaireToCommandTests {

  @Test
  public void convertirNull_DevraitRetournerNull() throws Exception {
    CommentaireToCommand converter = new CommentaireToCommand();

    CommentaireCommandObj command = converter.convert(null);

    assertNull(command, "Le CommandObj devrait être null");
  }

  @Test
  public void convertirCommentaireVide_DevraitRetournerCommandVide() throws Exception {
    CommentaireToCommand converter = new CommentaireToCommand();

    CommentaireCommandObj command = converter.convert(new Commentaire());

    assertNotNull(command, "Le CommandObj ne devrait pas être null");
  }

  @Test
  public void convertirCommentairePeuple_DevraitRetournerCommandPeuple() throws Exception {

    CommentaireToCommand converter = new CommentaireToCommand();
    Commentaire comm = new Commentaire();
    Long idAttendue = 1L;
    comm.setId(idAttendue);
    String commAttendu = "Un comm";
    comm.setCommentaire(commAttendu);

    CommentaireCommandObj command = converter.convert(comm);

    assertEquals(idAttendue, command.getId(), "L'id ne correspond pas");
    assertEquals(commAttendu, command.getCommentaire(), "Le commentaire ne correspond pas");
  }
}
