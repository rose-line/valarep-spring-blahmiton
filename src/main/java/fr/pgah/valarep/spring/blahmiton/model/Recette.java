package fr.pgah.valarep.spring.blahmiton.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Recette {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;
  private int tempsPrep;
  private int tempsCuisson;
  private int nbPersonnes;
  private String source;

  @Lob
  private String instructions;

  @Enumerated(EnumType.STRING)
  private Difficulte difficulte;

  @Lob
  private Byte[] image;

  @OneToOne(cascade = CascadeType.ALL)
  private Commentaire commentaire;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "recette")
  private Set<Ingredient> ingredients;

  @ManyToMany
  @JoinTable(name = "recette_categorie",
      joinColumns = @JoinColumn(name = "id_recette"),
      inverseJoinColumns = @JoinColumn(name = "id_categorie"))
  private Set<Categorie> categories;


}
