package com.example.projetangular.controllers;

import com.example.projetangular.entities.Categorie;
import com.example.projetangular.services.ICategorieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("categories")
public class categorieController {

    ICategorieService categorieService;


    @PostMapping("/add")
    Categorie addCategorie(@RequestBody Categorie categorie){
        return categorieService.addCategorie(categorie);
    }


    @GetMapping("/{id}")
    Categorie retrieveCategorie(@PathVariable Long id){
        return categorieService.getCategorie(id);
    }


    @GetMapping("")
    List<Categorie> retrieveCategories(){
        return categorieService.getAllCategorie();
    }


    @DeleteMapping("/{id}")
    void deleteCategorie(@PathVariable Long id){
        categorieService.deleteCategorie(id);
    }


    @PutMapping("")
    Categorie updateCategorie(@RequestBody Categorie categorie){
        return categorieService.updateCategorie(categorie);
    }


}
