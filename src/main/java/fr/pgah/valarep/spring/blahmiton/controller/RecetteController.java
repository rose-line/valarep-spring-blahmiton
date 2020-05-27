package fr.pgah.valarep.spring.blahmiton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import fr.pgah.valarep.spring.blahmiton.commandobj.RecetteCommandObj;
import fr.pgah.valarep.spring.blahmiton.model.Recette;
import fr.pgah.valarep.spring.blahmiton.service.ServiceRecettes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

  @RequestMapping("/recette/nouvelle")
  public String nouvelleRecette(Model model) {

    model.addAttribute("recette", new RecetteCommandObj());

    return "recette/recetteform";
  }

  @RequestMapping("/recette/modifier/{id}")
  public String modifierRecette(@PathVariable String id, Model model) {

    model.addAttribute("recette", serviceRecettes.findCommandById(Long.valueOf(id)));

    return "recette/recetteform";
  }

  @PostMapping("/recette")
  public String enregistrerRecette(@ModelAttribute RecetteCommandObj cmd) {

    RecetteCommandObj cmdEnregistree = serviceRecettes.saveRecetteCommandObj(cmd);

    return "redirect:/recette/voir/" + cmdEnregistree.getId();
  }

  @GetMapping
  @RequestMapping("recette/supprimer/{id}")
  public String supprimerRecette(@PathVariable String id) {

    serviceRecettes.deleteById(Long.valueOf(id));

    log.info("Recette supprimée : " + id);

    return "redirect:/";
  }
}
