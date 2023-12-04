package com.example.projetangular.controllers;

import com.example.projetangular.FileUpload;
import com.example.projetangular.entities.*;
import com.example.projetangular.services.IBibliothequeService;
import com.example.projetangular.services.IFoyerService;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")

@AllArgsConstructor
@RestController
@RequestMapping("bibliotheques")


public class BibliothequeController {
    IBibliothequeService bibliothequeService;
    IFoyerService foyerService;

    ILivreService livreService;



    @PostMapping("/addbibliotheque")
    public Bibliotheque addBibliotheque(
            @RequestParam("nomB") String nomB,
            @RequestParam("email") String email,
            @RequestParam("numTel") long numTel,
            @RequestParam("horaire") String horaire,
            @RequestParam("description") String description,
            @RequestParam("imageB") MultipartFile multipartFile) throws IOException, ParseException {

        Bibliotheque bibliotheque = new Bibliotheque();
        bibliotheque.setNomB(nomB);
        bibliotheque.setEmail(email);
        bibliotheque.setNumTel(numTel);
        bibliotheque.setHoraire(horaire);
        bibliotheque.setDescription(description);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        bibliotheque.setImageB(fileName);
        Bibliotheque SavedBiblio = bibliothequeService.addBibliotheque(bibliotheque);
        String uploadDir = "images_biblios/" + SavedBiblio.getIdBibliotheque();
        FileUpload.saveFile(uploadDir, fileName, multipartFile);
        return SavedBiblio;
    }
    @GetMapping("/{id}")
    Bibliotheque retrieveBibliotheque(@PathVariable Long id){

        return bibliothequeService.getBibliotheque(id);
    }
    @GetMapping("")
    List<Bibliotheque> retrieveBibliotheques(){

        return bibliothequeService.getAllBibliotheque();
    }
    @DeleteMapping("/{id}")
    void deleteBibliotheque(@PathVariable Long id){

        bibliothequeService.deleteBibliotheque(id);
    }
    @PutMapping("/updatebiblio/{id}")
    Bibliotheque updateBibliotheque(@PathVariable Long id ,@RequestBody Bibliotheque bibliotheque){

        return bibliothequeService.updateBibliotheque(id,bibliotheque);
    }

    @PutMapping("/aff/{nombibliotheque}/{nomFoyer}")
    public Bibliotheque affecterBibliothequeAFoyer(@PathVariable String nombibliotheque,@PathVariable String nomFoyer){

        return  bibliothequeService.affecterBibliothequeAFoyer(nombibliotheque,nomFoyer);
    }


    @PutMapping("/ajoutLivre/{idLivre}/{idBiblio}")
    public Bibliotheque ajouterLivreBiblio(@PathVariable Long idLivre,@PathVariable Long idBiblio){
        Livre livre  = livreService.getLivre(idLivre);
        Bibliotheque bilio = bibliothequeService.getBibliotheque(idBiblio);

        return  bibliothequeService.ajouterLivreBiblio(livre,bilio);
    }


    @GetMapping("/byfoyer/{idFoyer}")
    public Bibliotheque retrieveBibliothequeByFoyer(@PathVariable Long idFoyer){

        Foyer foyer = foyerService.getFoyer(idFoyer);
        return bibliothequeService.getBibliothequeByFoyer(foyer);
    }

    @GetMapping("/evenement/{idEvenement}")
    Bibliotheque retrieveBibliothequeByEvenement(@PathVariable Long idEvenement){
        return bibliothequeService.getBibliothequeByEvenement(idEvenement);
    }
}