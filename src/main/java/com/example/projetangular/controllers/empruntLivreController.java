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

    @DeleteMapping("/{id}")
    void deleteEmpruntLivre(@PathVariable Long id){
        empruntLivreService.deleteEmpruntLivre(id);
    }

    @PutMapping("/{id}")
    EmpruntLivre updateEmpruntLivre(@RequestBody EmpruntLivre empruntLivre)  {
        return empruntLivreService.updateEmpruntLivre(empruntLivre);
    }

    @PutMapping("/accepter/{id}")
    EmpruntLivre accepterEmpruntLivre(@PathVariable Long id)  {
        emailService.sendEmail("lina.laroussi@esprit.tn","Accpetation emprunt livre","nous avons accepté votre demande d'emprunt de livre vous pouvez venir à tout moment le récupérer");
        return empruntLivreService.accepterEmpruntLivre(id);
    }

    @DeleteMapping("/refuser/{id}/{idLivre}")
    void refuserEmpruntLivre(@PathVariable Long id , @PathVariable Long idLivre){
        Livre livre = livreService.getLivre(idLivre);
        livreService.updateDisponibiliteLivre(livre,true);
        emailService.sendEmail("lina.laroussi@esprit.tn","Refus emprunt livre","nous avons refusé votre demande d'emprunt de livre vous pouvez voir un autre jour");
        empruntLivreService.refuserEmpruntLivre(id);
    }

}
