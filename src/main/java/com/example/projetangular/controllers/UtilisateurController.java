package com.example.projetangular.controllers;


import com.example.projetangular.entities.Utilisateur;
import com.example.projetangular.services.IUtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("utilisateurs")
public class UtilisateurController {


    IUtilisateurService utilisateurService;

    @GetMapping("/emprunt/{idEmprunt}")
    Utilisateur getUtilisateurByEmprunt(@PathVariable Long idEmprunt){
        return utilisateurService.getUtilisateurByEmprunt(idEmprunt);
    }



}
