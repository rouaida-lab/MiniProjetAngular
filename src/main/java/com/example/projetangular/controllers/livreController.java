package com.example.projetangular.controllers;
import com.example.projetangular.FileUploadUtil;
import com.example.projetangular.entities.Categorie;
import com.example.projetangular.entities.Livre;
import com.example.projetangular.entities.Utilisateur;
import com.example.projetangular.services.ICategorieService;
import com.example.projetangular.services.ILivreService;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        livre.setDisponibilite(true);

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


    @GetMapping("/maxEmprunts")
    Livre getLivreWithMaxEmprunts(){
        return livreService.getLivreWithMaxEmprunts();
    }

    @GetMapping("/category/{idCategory}")
    List<Livre> retrieveLivresByCategory(@PathVariable Long idCategory){

        Categorie categorie = categorieService.getCategorie(idCategory);
        return livreService.getAllLivreByCategory(categorie);
    }


    @GetMapping("/emprunt/{idEmprunt}")
    Livre getLivreByEmprunt(@PathVariable Long idEmprunt){
        return livreService.getLivreByEmprunt(idEmprunt);
    }


    @DeleteMapping("/{id}")
    void deleteLivre(@PathVariable Long id){
        Livre livre = livreService.getLivre(id);

        String cheminImage ="images_livres/" + livre.getIdLivre() + "/" + livre.getImage();
        Path cheminFichier = Paths.get(cheminImage);

        try {
            Files.delete(cheminFichier);

            Path cheminDossier = cheminFichier.getParent();
            Files.delete(cheminDossier);

        } catch (Exception e) {
            e.printStackTrace();
        }

        livreService.deleteLivre(id);
    }


    @PutMapping("/{idLivre}")
    Livre updateLivre(@PathVariable Long idLivre,
                      @RequestParam("titre") String titre,
                      @RequestParam("description") String description,
                      @RequestParam("nomAuteur") String nomAuteur,
                      @RequestParam("nbPages") Integer nbPages,
                      @RequestParam("dateDePublication") String dateDePublication,
                      @RequestParam("idCategorie") String idCategorie,
                      @RequestParam("disponibilite") String disponibilite,
                      @RequestParam("image") MultipartFile multipartFile) throws ParseException, IOException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(dateDePublication);

        long idCategorieLong = Long.parseLong(idCategorie);
        long disp = Long.parseLong(disponibilite);

        Categorie categorie = categorieService.getCategorie(idCategorieLong);

        Livre livre = livreService.getLivre(idLivre);

        livre.setTitre(titre);
        livre.setDescription(description);
        livre.setNomAuteur(nomAuteur);
        livre.setNbPages(nbPages);
        livre.setDateDePublication(parsedDate);
        livre.setCategorie(categorie);

        if(disp == 1){
            livre.setDisponibilite(true);
        } else if (disp == 0) {
            livre.setDisponibilite(false);
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        livre.setImage(fileName);

        Livre updatedLivre = livreService.updateLivre(livre);

        String uploadDir = "images_livres/" + updatedLivre.getIdLivre();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return updatedLivre;

    }

}
