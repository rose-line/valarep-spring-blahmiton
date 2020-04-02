package fr.pgah.valarep.spring.blahmiton;

import java.util.HashSet;
import java.util.Set;

public class Parking {

  // État (attributs)

  private Set<String> immatriculationsAutorisees;
  private Set<String> immatriculationsPresentes;
  private final int capacite;

  // Comportements (méthodes)

  public Parking() {
    capacite = 100;
    immatriculationsAutorisees = new HashSet<>();
    immatriculationsPresentes = new HashSet<>();
  }

  public Parking(int capacite) {
    this.capacite = capacite;
    immatriculationsAutorisees = new HashSet<>();
    immatriculationsPresentes = new HashSet<>();
  }

  public void autoriserImmatriculation(String immatriculation) {
    immatriculationsAutorisees.add(immatriculation);
  }

  public boolean enregistrerEntree(String immatriculation) {
    if (estPlein() || !estAutorise(immatriculation)) {
      return false;
    }
    immatriculationsPresentes.add(immatriculation);
    return true;
  }

  public boolean enregistrerSortie(String immatriculation) {
    if (!estPresent(immatriculation)) {
      return false;
    }
    immatriculationsPresentes.remove(immatriculation);
    return true;
  }

  public boolean estPresent(String immatriculation) {
    return immatriculationsPresentes.contains(immatriculation);
  }

  public boolean estPlein() {
    return immatriculationsPresentes.size() == capacite;
  }

  public int nbVehiculesPresents() {
    return immatriculationsPresentes.size();
  }

  public void afficherVehiculesPresents() {
    System.out.println("Liste des véhicules présents : ");
    afficherVehicules(immatriculationsPresentes);
  }

  public void afficherVehiculesAutorises() {
    System.out.println("Liste des véhicules autorisés : ");
    afficherVehicules(immatriculationsAutorisees);
  }

  public void afficherTauxRemplissage() {
    System.out.println("Taux de remplissage : " + tauxRemplissage());
  }

  public double tauxRemplissage() {
    double taux = (double) nbVehiculesPresents() / capacite * 100;
    return Math.round(taux);
  }

  private void afficherVehicules(Set<String> immatriculations) {
    for (String immatriculation : immatriculations) {
      System.out.println(immatriculation);
    }
  }

  private boolean estAutorise(String immatriculation) {
    return immatriculationsAutorisees.contains(immatriculation);
  }

  public Set<String> getImmatriculationsAutorisees() {
    return immatriculationsAutorisees;
  }

  public void setImmatriculationsAutorisees(Set<String> immatriculationsAutorisees) {
    this.immatriculationsAutorisees = immatriculationsAutorisees;
  }

  public Set<String> getImmatriculationsPresentes() {
    return immatriculationsPresentes;
  }

  public void setImmatriculationsPresentes(Set<String> immatriculationsPresentes) {
    this.immatriculationsPresentes = immatriculationsPresentes;
  }

  public int getCapacite() {
    return capacite;
  }
}
