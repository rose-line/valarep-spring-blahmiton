package fr.pgah.valarep.spring.blahmiton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    // SpringApplication.run(DemoApplication.class, args);

    int capacite = 100;
    Parking parking = new Parking(capacite);
    Parking parking2 = new Parking();

  }

}
