package com.example.projetangular.controllers;
import com.example.projetangular.FileUploadUtil;
import com.example.projetangular.entities.Categorie;
import com.example.projetangular.entities.Livre;
import com.example.projetangular.services.ICategorieService;
import com.example.projetangular.services.ILivreService;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("livres")
public class livreController {

    ILivreService livreService;

    ICategorieService categorieService;


    @PostMapping("/add")
    Livre addLivre(@RequestParam("titre") String titre,
                   @RequestParam("description") String description,
                   @RequestParam("nomAuteur") String nomAuteur,
                   @RequestParam("nbPages") Integer nbPages,
                   @RequestParam("dateDePublication") String dateDePublication,
                   @RequestParam("idCategorie") String idCategorie,
                   @RequestParam("image") MultipartFile multipartFile) throws IOException, ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(dateDePublication);

        long idCategorieLong = Long.parseLong(idCategorie);


        Livre livre = new Livre();

        Categorie categorie = categorieService.getCategorie(idCategorieLong);
        livre.setTitre(titre);
        livre.setDescription(description);
        livre.setNomAuteur(nomAuteur);
        livre.setNbPages(nbPages);
        livre.setDateDePublication(parsedDate);
        livre.setCategorie(categorie);

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        livre.setImage(fileName);

        Livre SavedLivre = livreService.addLivre(livre);

        String uploadDir = "images_livres/" + SavedLivre.getIdLivre();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return SavedLivre;

    }


    @GetMapping("/{id}")
    Livre retrieveLivre(@PathVariable Long id){
        return livreService.getLivre(id);
    }


    @GetMapping("")
    List<Livre> retrieveLivres(){
        return livreService.getAllLivre();
    }


    @DeleteMapping("/{id}")
    void deleteLivre(@PathVariable Long id){
        livreService.deleteLivre(id);
    }


    @PutMapping("")
    Livre updateLivre(@RequestBody Livre livre){
        return livreService.updateLivre(livre);
    }

}
