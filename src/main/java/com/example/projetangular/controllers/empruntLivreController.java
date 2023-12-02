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
import org.thymeleaf.context.Context;

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


    @PostMapping("/add/admin")
    EmpruntLivre addEmpruntLivreAdmin(@RequestParam("email") String email , @RequestParam("dateDebutEmprunt") String dateDebutEmprunt , @RequestParam("dateFinEmprunt") String dateFinEmprunt , @RequestParam("idLivre") Long idLivre) throws ParseException {

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
        empruntLivre.setEtat("valide");

        livreService.updateDisponibiliteLivre(livre,false);

        return empruntLivreService.addEmpruntLivre(empruntLivre);
    }


    @GetMapping("/{id}")
    EmpruntLivre retrieveEmpruntLivre(@PathVariable Long id){

        return empruntLivreService.getEmpruntLivre(id);
    }


    @GetMapping("/count/total")
    Integer countEmpruntLivre(){
        return empruntLivreService.countEmpruntLivres();
    }

    @GetMapping("/count/Encours")
    Integer countEmpruntLivreEnCours(){
        return empruntLivreService.countEmpruntLivresEncours();
    }

    @GetMapping("/count/Accepte")
    Integer countEmpruntLivreAccepte(){
        return empruntLivreService.countEmpruntLivresAccepte();
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
    EmpruntLivre accepterEmpruntLivre(@PathVariable Long id , @RequestParam("email") String email ,  @RequestParam("idLivre") String idLivre )  {

        long idLivreLong = Long.parseLong(idLivre);
        Livre livre = livreService.getLivre(idLivreLong);
        livre.incrementerNbEmprunts();

        Context context = new Context();
        context.setVariable("title", "Votre demande d'emprunt a été accepté");
        context.setVariable("content", "Nous sommes ravis de vous informer que votre demande d'emprunt pour le livre " + livre.getTitre() +" a été acceptée. Vous êtes invité à venir récupérer ce livre à tout moment qui vous convient. N'hésitez pas à passer à notre bibliothèque à votre convenance. Si vous avez des questions supplémentaires, n'hésitez pas à nous contacter. Merci et à bientôt !");
        context.setVariable("imageResourceName", "accepter.png");
        emailService.sendEmail(email,"Accpetation emprunt livre","templateEmail", context);
        return empruntLivreService.accepterEmpruntLivre(id);
    }

    @DeleteMapping("/refuser/{id}/{idLivre}/{idUser}")
    void refuserEmpruntLivre(@PathVariable Long id , @PathVariable Long idLivre , @PathVariable Long idUser){
        String  email = utilisateurService.getEmailUtilisateur(idUser);
        Livre livre = livreService.getLivre(idLivre);
        livreService.updateDisponibiliteLivre(livre,true);
        Context context = new Context();
        context.setVariable("title", "Votre demande d'emprunt a été refusé");
        context.setVariable("content", "Nous regrettons de vous informer que votre demande d'emprunt pour le livre : " + livre.getTitre() + " a été refusée. Nous vous encourageons à explorer notre collection de livres disponibles et à soumettre de nouvelles demandes. N'hésitez pas à nous contacter si vous avez des questions ou si nous pouvons vous aider de quelque manière que ce soit. Merci de votre compréhension");
        context.setVariable("imageResourceName", "refuser.png");
        emailService.sendEmail(email,"Refus emprunt livre","templateEmail",context);
        empruntLivreService.refuserEmpruntLivre(id);
    }

}
