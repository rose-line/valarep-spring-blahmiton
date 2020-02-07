package fr.pgah.valarep.spring.blahmiton.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nom;
  private Double quantite;

  @OneToOne
  private UniteDeMesure uniteDeMesure;

  @ManyToOne
  private Recette recette;

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

  public Double getQuantite() {
    return quantite;
  }

  public void setQuantite(Double quantite) {
    this.quantite = quantite;
  }

  public UniteDeMesure getUniteDeMesure() {
    return uniteDeMesure;
  }

  public void setUniteDeMesure(UniteDeMesure uniteDeMesure) {
    this.uniteDeMesure = uniteDeMesure;
  }

  public Recette getRecette() {
    return recette;
  }

  public void setRecette(Recette recette) {
    this.recette = recette;
  }

}
