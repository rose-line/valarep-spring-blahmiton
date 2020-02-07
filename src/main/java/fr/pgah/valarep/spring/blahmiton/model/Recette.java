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
  private String url;

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getTempsPrep() {
    return tempsPrep;
  }

  public void setTempsPrep(int tempsPrep) {
    this.tempsPrep = tempsPrep;
  }

  public int getTempsCuisson() {
    return tempsCuisson;
  }

  public void setTempsCuisson(int tempsCuisson) {
    this.tempsCuisson = tempsCuisson;
  }

  public int getNbPersonnes() {
    return nbPersonnes;
  }

  public void setNbPersonnes(int nbPersonnes) {
    this.nbPersonnes = nbPersonnes;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }
}
