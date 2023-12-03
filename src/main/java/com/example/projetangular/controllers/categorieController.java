package com.example.projetangular.controllers;

import com.example.projetangular.FileUploadUtil;
import com.example.projetangular.entities.Categorie;
import com.example.projetangular.services.ICategorieService;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        Categorie categorie = categorieService.getCategorie(id);
        String cheminImage ="images/" + categorie.getIdCategorie() + "/" + categorie.getImage();
        Path cheminFichier = Paths.get(cheminImage);

        try {
            Files.delete(cheminFichier);

            Path cheminDossier = cheminFichier.getParent();
            Files.delete(cheminDossier);

        } catch (Exception e) {
            e.printStackTrace();
        }

        categorieService.deleteCategorie(id);
    }


    @GetMapping("/livre/{idLivre}")
    Categorie retrieveCategorieByLivre(@PathVariable Long idLivre){
        return categorieService.getCategorieDuLivre(idLivre);
    }

    @PutMapping("/{id}")
    Categorie updateCategorie(@PathVariable Long id ,@RequestParam("nom") String nom,
                              @RequestParam("description") String description ,
                              @RequestParam("image") MultipartFile multipartFile) throws IOException {

        Categorie categorie = categorieService.getCategorie(id);
        categorie.setNom(nom);
        categorie.setDescription(description);

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        categorie.setImage(fileName);
        Categorie updatedcategorie = categorieService.updateCategorie(categorie);

        String uploadDir = "images/" + updatedcategorie.getIdCategorie();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return updatedcategorie;
    }


}
