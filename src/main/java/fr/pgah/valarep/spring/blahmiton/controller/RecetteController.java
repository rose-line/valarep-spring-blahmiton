package fr.pgah.valarep.spring.blahmiton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import fr.pgah.valarep.spring.blahmiton.model.Recette;
import fr.pgah.valarep.spring.blahmiton.service.ServiceRecettes;

@Controller
public class RecetteController {

  private ServiceRecettes serviceRecettes;

  public RecetteController(ServiceRecettes service) {
    this.serviceRecettes = service;
  }

  @RequestMapping("/recette/voir/{id}")
  public String voirId(@PathVariable String id, Model model) {

    Recette recette = serviceRecettes.findById(Long.valueOf(id));
    model.addAttribute("recette", recette);

    return "recette/voir";
  }
}
