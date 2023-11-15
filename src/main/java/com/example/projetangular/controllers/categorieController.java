package com.example.projetangular.controllers;

import com.example.projetangular.FileUploadUtil;
import com.example.projetangular.entities.Categorie;
import com.example.projetangular.services.ICategorieService;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("categories")
public class categorieController {

    ICategorieService categorieService;


    @PostMapping("/add")
    Categorie addCategorie(@RequestParam("nom") String nom,
                           @RequestParam("description") String description , @RequestParam("image") MultipartFile multipartFile) throws IOException {

        Categorie categorie = new Categorie();
        categorie.setNom(nom);
        categorie.setDescription(description);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        categorie.setImage(fileName);
        Categorie Savedcategorie = categorieService.addCategorie(categorie);

        String uploadDir = "images/" + Savedcategorie.getIdCategorie();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return Savedcategorie;
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
