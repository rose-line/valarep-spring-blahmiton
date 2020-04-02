package fr.pgah.valarep.spring.blahmiton;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
class DemoApplicationTests {

  @Test
  void contextLoads() {
  }

  @Test
  public void testParking() {
    Parking parking = new Parking(5);

    // Listes vides au départ
    assertEquals("Liste autorisés pas vide", 0, parking.getImmatriculationsAutorisees().size());
    assertEquals("Liste présents pas vide", 0, parking.getImmatriculationsPresentes().size());

    // Ajout immatriculations autorisées

    parking.autoriserImmatriculation("AA-123-AA");
    parking.autoriserImmatriculation("BB-123-AA");
    parking.autoriserImmatriculation("CC-123-AA");
    parking.autoriserImmatriculation("DD-123-AA");
    parking.autoriserImmatriculation("EE-123-AA");
    parking.autoriserImmatriculation("FF-123-AA");

    assertEquals("devrait avoir 6 immatriculations autorisée", 6,
        parking.getImmatriculationsAutorisees().size());

    // Ajout de véhicules dans parking

    parking.enregistrerEntree("AA-123-AA");

    assertEquals("Véhicule pas rentré", 1, parking.nbVehiculesPresents());

    // Véhicule non autorisé

    boolean entré = parking.enregistrerEntree("ZZ-123-AA");

    assertEquals("devrait pas être rentré", false, entré);
    assertEquals("devrait pas être rentré", 1, parking.nbVehiculesPresents());

    // Test de sortie

    boolean sorti = parking.enregistrerSortie("YY-123-AA");

    assertEquals("devrait pas être sorti", false, sorti);
    assertEquals("devrait pas être sorti", 1, parking.nbVehiculesPresents());

    // Test Véhicule présent

    boolean présent = parking.estPresent("AA-123-AA");

    assertEquals("devrait être présent", true, présent);

    // Test Véhicule non présent

    présent = parking.estPresent("YY-123-AA");

    assertEquals("devrait être absent", false, présent);

    // Test parking pas plein

    boolean plein = parking.estPlein();

    assertEquals("devrait pas être plein", false, plein);

    // Test parking plein

    parking.enregistrerEntree("BB-123-AA");
    parking.enregistrerEntree("CC-123-AA");
    parking.enregistrerEntree("DD-123-AA");
    parking.enregistrerEntree("EE-123-AA");

    plein = parking.estPlein();

    assertEquals("devrait être plein", true, plein);

    // Ne devrait pas pouvoir ajouter si parking plein

    entré = parking.enregistrerEntree("FF-123-AA");

    assertEquals("ne devrait pas entrer", false, entré);
    assertEquals("ne devrait pas entrer", 5, parking.nbVehiculesPresents());

    // Test taux remplissage

    double taux = parking.tauxRemplissage();

    assertEquals("taux devrait être 100 %", 100, taux, 0.01);

    // Test de sortie valide

    sorti = parking.enregistrerSortie("CC-123-AA");

    assertEquals("devrait être sorti", true, sorti);
    assertEquals("devrait être sorti", 4, parking.nbVehiculesPresents());
    assertEquals("taux de remplissage devrait 80 %", 80, parking.tauxRemplissage(), 0.01);
  }

}
