package fr.pgah.valarep.spring.blahmiton.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import fr.pgah.valarep.spring.blahmiton.model.Categorie;
import fr.pgah.valarep.spring.blahmiton.model.UniteDeMesure;
import fr.pgah.valarep.spring.blahmiton.repo.CategorieRepo;
import fr.pgah.valarep.spring.blahmiton.repo.UniteDeMesureRepo;

@Controller
public class IndexController {

  private CategorieRepo catRepo;
  private UniteDeMesureRepo udmRepo;

  public IndexController(CategorieRepo catRepo, UniteDeMesureRepo udmRepo) {
    this.catRepo = catRepo;
    this.udmRepo = udmRepo;
  }

  @RequestMapping({"", "/", "/index", "/index.htm", "/index.html"})
  public String getIndex() {

    List<Categorie> categories = catRepo.findByNom("Italien");
    Categorie cat = categories.get(0);
    List<UniteDeMesure> udms = udmRepo.findByNom("cas");
    UniteDeMesure udm = udms.get(0);

    System.out.println("cat : " + cat.getId() + " " + cat.getNom());
    System.out.println("udm : " + udm.getId() + " " + udm.getNom());

    return "index";
  }



}
