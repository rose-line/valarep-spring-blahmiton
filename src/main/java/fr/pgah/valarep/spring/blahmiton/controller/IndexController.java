package fr.pgah.valarep.spring.blahmiton.controller;

import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import fr.pgah.valarep.spring.blahmiton.model.Recette;
import fr.pgah.valarep.spring.blahmiton.service.ServiceRecettes;

@Controller
public class IndexController {

  private ServiceRecettes serviceRecettes;

  public IndexController(ServiceRecettes service) {
    this.serviceRecettes = service;
  }

  @RequestMapping({"", "/", "/index", "/index.htm", "/index.html"})
  public String getIndex(Model model) {

    Set<Recette> recettes = serviceRecettes.getRecettes();
    model.addAttribute("recettes", recettes);

    return "index";
  }
}
