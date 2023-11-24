package com.example.projetangular.controllers;

import com.example.projetangular.entities.EmpruntLivre;
import com.example.projetangular.entities.Livre;
import com.example.projetangular.entities.Utilisateur;
import com.example.projetangular.services.EmailService;
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

    EmailService emailService;


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

        livreService.updateDisponibiliteLivre(livre,false);

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

    @DeleteMapping("/{id}/{idLivre}")
    void deleteEmpruntLivre(@PathVariable Long id , @PathVariable Long idLivre){

        empruntLivreService.deleteEmpruntLivre(id);
        Livre livre = livreService.getLivre(idLivre);
        livreService.updateDisponibiliteLivre(livre,true);

    }

    @PutMapping("/{id}")
    EmpruntLivre updateEmpruntLivre(@RequestParam("dateDebutEmprunt") String dateDebutEmprunt , @RequestParam("dateFinEmprunt") String dateFinEmprunt , @RequestParam("idLivre") Long idLivre, @RequestParam("idAncienLivre") Long idAncienLivre , @PathVariable Long id) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDateDebut = dateFormat.parse(dateDebutEmprunt);
        Date parsedDateFin = dateFormat.parse(dateFinEmprunt);

        EmpruntLivre empruntlivre = empruntLivreService.getEmpruntLivre(id);
        empruntlivre.setDateDebutEmprunt(parsedDateDebut);
        empruntlivre.setDateFinEmprunt(parsedDateFin);
        Livre ancienlivre = livreService.getLivre(idAncienLivre);
        livreService.updateDisponibiliteLivre(ancienlivre,true);
        Livre livre = livreService.getLivre(idLivre);
        livreService.updateDisponibiliteLivre(livre,false);
        empruntlivre.setLivre(livre);

        return empruntLivreService.updateEmpruntLivre(empruntlivre);
    }

    @PutMapping("/accepter/{id}")
    EmpruntLivre accepterEmpruntLivre(@PathVariable Long id , @RequestBody String email)  {

        emailService.sendEmail(email,"Accpetation emprunt livre","nous avons accepté votre demande d'emprunt de livre vous pouvez venir à tout moment le récupérer");
        return empruntLivreService.accepterEmpruntLivre(id);
    }

    @DeleteMapping("/refuser/{id}/{idLivre}/{idUser}")
    void refuserEmpruntLivre(@PathVariable Long id , @PathVariable Long idLivre , @PathVariable Long idUser){
        String  email = utilisateurService.getEmailUtilisateur(idUser);
        Livre livre = livreService.getLivre(idLivre);
        livreService.updateDisponibiliteLivre(livre,true);
        emailService.sendEmail(email,"Refus emprunt livre","nous avons refusé votre demande d'emprunt de livre vous pouvez voir un autre jour");
        empruntLivreService.refuserEmpruntLivre(id);
    }

}
