package fr.pgah.valarep.spring.blahmiton.dummyData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import fr.pgah.valarep.spring.blahmiton.model.Categorie;
import fr.pgah.valarep.spring.blahmiton.model.Commentaire;
import fr.pgah.valarep.spring.blahmiton.model.Difficulte;
import fr.pgah.valarep.spring.blahmiton.model.Ingredient;
import fr.pgah.valarep.spring.blahmiton.model.Recette;
import fr.pgah.valarep.spring.blahmiton.model.UniteDeMesure;
import fr.pgah.valarep.spring.blahmiton.repo.CategorieRepo;
import fr.pgah.valarep.spring.blahmiton.repo.RecetteRepo;
import fr.pgah.valarep.spring.blahmiton.repo.UniteDeMesureRepo;

@Component
public class DummyRecettes implements ApplicationListener<ContextRefreshedEvent> {

  private UniteDeMesureRepo udmRepo;
  private CategorieRepo catRepo;
  private RecetteRepo recetteRepo;

  public DummyRecettes(UniteDeMesureRepo udmRepo, CategorieRepo categorieRepo,
      RecetteRepo recetteRepo) {
    this.udmRepo = udmRepo;
    this.catRepo = categorieRepo;
    this.recetteRepo = recetteRepo;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {

    recetteRepo.saveAll(creerRecettes());

  }

  private List<Recette> creerRecettes() {

    List<Recette> recettes = new ArrayList<>();

    UniteDeMesure gramme = udmRepo.findByNom("g").get(0);
    UniteDeMesure litre = udmRepo.findByNom("l").get(0);
    UniteDeMesure unite = udmRepo.findByNom("unite").get(0);

    Categorie italien = catRepo.findByNom("Italien").get(0);
    Categorie froid = catRepo.findByNom("Froid").get(0);

    // List<Categorie> italienList = catRepo.findByNom("Italien");
    // Categorie catItalien2 = italienList.get(0);

    Recette pateAPizza = new Recette();
    pateAPizza.setDescription("Pâte à pizza");
    pateAPizza.setNbPersonnes(2);
    pateAPizza.setTempsPrep(60);
    pateAPizza.setTempsCuisson(10);
    pateAPizza.setDifficulte(Difficulte.FACILE);
    pateAPizza.setSource("https://www.marmiton.org/recettes/recette_pate-a-pizza-fine_29544.aspx");
    pateAPizza.setInstructions(
        "Verser la farine dans un grand saladier et y faire un puits.\nVerser la levure, le sel et le sucre dans un verre doseur. Ajouter 1/4 de litre d'eau tiède, bien mélanger. Couvrir d'un film plastique et laisser reposer environ 10 minutes (jusqu'à l'apparition d'une mousse brune bien épaisse en surface).\nVerser le mélange du verre doseur dans le saladier, mélanger à la main. Puis quand la pâte n'est plus collante pétrir environ 5 minutes à la main sur une surface farinée.\nArroser d'un peu d'huile d'olive, puis pétrir à nouveau environ 5 minutes.\nLaisser reposer au moins 1 h sous un linge humide dans un endroit tiède (à côté d'un radiateur, d'une cheminée ou d'un poêle en hiver).\nAprès repos, pétrir quelques instants puis diviser la pâte en trois boules égales (on peut en congeler).\nEtaler la pâte sur une surface farinée. Badigeonner la plaque d'un peu d'huile d'olive avant d'y appliquer la pâte.\nCuisson 10 minutes à 220°C (thermostat 7-8).");

    Commentaire comm = new Commentaire();
    comm.setCommentaire("C'est très très bon. Miam miam.");
    comm.setRecette(pateAPizza);
    pateAPizza.setCommentaire(comm);

    Set<Ingredient> ingredients = new HashSet<>();

    Ingredient ingredient1 = new Ingredient();
    ingredient1.setNom("farine");
    ingredient1.setQuantite(500.0);
    ingredient1.setUniteDeMesure(gramme);
    ingredient1.setRecette(pateAPizza);

    Ingredient ingredient2 = new Ingredient();
    ingredient2.setNom("eau");
    ingredient2.setQuantite(0.25);
    ingredient2.setUniteDeMesure(litre);
    ingredient2.setRecette(pateAPizza);

    Ingredient ingredient3 = new Ingredient();
    ingredient3.setNom("sachet de levure");
    ingredient3.setQuantite(1.0);
    ingredient3.setUniteDeMesure(unite);
    ingredient3.setRecette(pateAPizza);

    ingredients.add(ingredient1);
    ingredients.add(ingredient2);
    ingredients.add(ingredient3);

    pateAPizza.setIngredients(ingredients);

    Set<Categorie> categories = new HashSet<>();

    categories.add(italien);

    pateAPizza.setCategories(categories);

    recettes.add(pateAPizza);

    Recette tiramisu = new Recette();
    tiramisu.setDescription("Tiramisu au speculoos");
    tiramisu.setNbPersonnes(6);
    tiramisu.setTempsPrep(20);
    tiramisu.setTempsCuisson(0);
    tiramisu.setDifficulte(Difficulte.MOYENNE);
    tiramisu.setSource("");
    tiramisu.setInstructions(
        "Mettez tout dans un plat et mélanger");

    Commentaire commTira = new Commentaire();
    commTira.setCommentaire("C'est très très bon aussi.");
    commTira.setRecette(tiramisu);
    tiramisu.setCommentaire(commTira);

    Set<Ingredient> ingredientsTira = new HashSet<>();

    Ingredient ingredient1Tira = new Ingredient();
    ingredient1Tira.setNom("mascarpone");
    ingredient1Tira.setQuantite(250.0);
    ingredient1Tira.setUniteDeMesure(gramme);
    ingredient1Tira.setRecette(tiramisu);

    Ingredient ingredient2Tira = new Ingredient();
    ingredient2Tira.setNom("speculoos");
    ingredient2Tira.setQuantite(30.0);
    ingredient2Tira.setUniteDeMesure(unite);
    ingredient2Tira.setRecette(tiramisu);

    ingredientsTira.add(ingredient1Tira);
    ingredientsTira.add(ingredient2Tira);

    tiramisu.setIngredients(ingredientsTira);

    Set<Categorie> categoriesTira = new HashSet<>();

    categoriesTira.add(italien);
    categoriesTira.add(froid);

    tiramisu.setCategories(categoriesTira);

    recettes.add(tiramisu);

    return recettes;
  }
}
