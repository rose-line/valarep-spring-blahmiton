package fr.pgah.valarep.spring.blahmiton.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Categorie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nom;

  @ManyToMany(mappedBy = "categories")
  private Set<Recette> recettes;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public Set<Recette> getRecettes() {
    return recettes;
  }

  public void setRecettes(Set<Recette> recettes) {
    this.recettes = recettes;
  }

}
