package com.example.projetangular.controllers;

import com.example.projetangular.entities.EmpruntLivre;
import com.example.projetangular.entities.Livre;
import com.example.projetangular.entities.Utilisateur;
import com.example.projetangular.services.IEmpruntLivreService;
import com.example.projetangular.services.ILivreService;
import com.example.projetangular.services.IUtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("emprunts")
public class empruntLivreController {

    IEmpruntLivreService empruntLivreService;

    ILivreService livreService;

    IUtilisateurService utilisateurService;


    @PostMapping("/add")
    EmpruntLivre addEmpruntLivre(@RequestParam("email") String email , @RequestParam("dateDebutEmprunt") String dateDebutEmprunt , @RequestParam("dateFinEmprunt") String dateFinEmprunt , @RequestParam("idLivre") Long idLivre) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDateDebut = dateFormat.parse(dateDebutEmprunt);
        Date parsedDateFin = dateFormat.parse(dateFinEmprunt);

        Livre livre = livreService.getLivre(idLivre);
        Utilisateur etudiant = utilisateurService.getUtilisateurByEmail(email);

        EmpruntLivre empruntLivre = new EmpruntLivre();
        empruntLivre.setDateDebutEmprunt(parsedDateDebut);
        empruntLivre.setDateFinEmprunt(parsedDateFin);
        empruntLivre.setEtudiantEmp(etudiant);
        empruntLivre.setLivre(livre);
        empruntLivre.setEtat("non valide");

        return empruntLivreService.addEmpruntLivre(empruntLivre);
    }


    @GetMapping("/{id}")
    EmpruntLivre retrieveEmpruntLivre(@PathVariable Long id){

        return empruntLivreService.getEmpruntLivre(id);
    }

    @GetMapping("")
    List<EmpruntLivre> retrieveEmpruntLivres(){
        return empruntLivreService.getAllEmpruntLivres();
    }

    @DeleteMapping("/{id}")
    void deleteEmpruntLivre(@PathVariable Long id){
        empruntLivreService.deleteEmpruntLivre(id);
    }

    @PutMapping("/{id}")
    EmpruntLivre updateEmpruntLivre(@RequestBody EmpruntLivre empruntLivre)  {
        return empruntLivreService.updateEmpruntLivre(empruntLivre);
    }

}
