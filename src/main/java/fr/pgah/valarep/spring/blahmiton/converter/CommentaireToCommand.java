package fr.pgah.valarep.spring.blahmiton.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import fr.pgah.valarep.spring.blahmiton.commandobj.CommentaireCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Commentaire;

@Component
public class CommentaireToCommand implements Converter<Commentaire, CommentaireCommandObj> {

  @Nullable
  @Override
  public CommentaireCommandObj convert(Commentaire source) {

    if (source == null) {
      return null;
    }

    final CommentaireCommandObj commentaireCommandObj = new CommentaireCommandObj();
    commentaireCommandObj.setId(source.getId());
    commentaireCommandObj.setCommentaire(source.getCommentaire());

    return commentaireCommandObj;
  }
}
