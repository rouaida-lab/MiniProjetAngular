package com.example.projetangular.controllers;

import com.example.projetangular.entities.Evenement;
import com.example.projetangular.services.EvenementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class EvenementController {
    EvenementService evenementService;

@PostMapping("/addEvenement")
Evenement addEvenement(@RequestBody Evenement evenement){

    return evenementService.addEvenement(evenement);
}
    @PostMapping("/Evenement/{id}")
    Evenement retrieveEvenement(@PathVariable Long id){

        return evenementService.getEvenement(id);
    }
    @PostMapping("/Evenement")
    List<Evenement> retrieveEvenements(){

        return evenementService.getAllEvenement();
    }
    @DeleteMapping("/Evenement/{id}")
    void deleteEvenement(@PathVariable Long id){

        evenementService.deleteEvenement(id);
    }
    @PutMapping("/Evenement")
    Evenement updateEvenement(@RequestBody Evenement evenement){

        return evenementService.updateEvenement(evenement);
    }

    @PutMapping("/bloc/aff/{nomEvenement}/{nomBibliotheque}")
    public Evenement affecterEvenementABiblio(@PathVariable String nomEvenement,@PathVariable String nomBibliotheque){

        return  evenementService.affecterEvenementABibliotheque(nomEvenement,nomBibliotheque);
    }


}