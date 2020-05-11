package fr.pgah.valarep.spring.blahmiton.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import fr.pgah.valarep.spring.blahmiton.commandobj.CommentaireCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Commentaire;

@Component
public class CommandToCommentaire implements Converter<CommentaireCommandObj, Commentaire> {

  @Nullable
  @Override
  public Commentaire convert(CommentaireCommandObj source) {

    if (source == null) {
      return null;
    }

    final Commentaire commentaire = new Commentaire();
    commentaire.setId(source.getId());
    commentaire.setCommentaire(source.getCommentaire());

    return commentaire;
  }
}
