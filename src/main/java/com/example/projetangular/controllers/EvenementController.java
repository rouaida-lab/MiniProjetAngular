package com.example.projetangular.controllers;

import com.example.projetangular.entities.Evenement;
import com.example.projetangular.services.IEvenementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")

@AllArgsConstructor
@RestController
@RequestMapping("evenements")

public class EvenementController {
    IEvenementService evenementService;

@PostMapping("/addEvenement")
Evenement addEvenement(@RequestBody Evenement evenement){

    return evenementService.addEvenement(evenement);
}
    @GetMapping("/{id}")
    Evenement retrieveEvenement(@PathVariable Long id){

        return evenementService.getEvenement(id);
    }
    @GetMapping("")
    List<Evenement> retrieveEvenements(){

        return evenementService.getAllEvenement();
    }
    @DeleteMapping("/{id}")
    void deleteEvenement(@PathVariable Long id){

        evenementService.deleteEvenement(id);
    }
    @PutMapping("")
    Evenement updateEvenement(@RequestBody Evenement evenement){

        return evenementService.updateEvenement(evenement);
    }

    @PutMapping("/aff/{nomEvenement}/{nomBibliotheque}")
    public Evenement affecterEvenementABiblio(@PathVariable String nomEvenement,@PathVariable String nomBibliotheque){

        return  evenementService.affecterEvenementABibliotheque(nomEvenement,nomBibliotheque);
    }


}