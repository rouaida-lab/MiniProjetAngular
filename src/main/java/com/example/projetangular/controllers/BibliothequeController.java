package com.example.projetangular.controllers;

import com.example.projetangular.entities.Bibliotheque;
import com.example.projetangular.services.BibliothequeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@AllArgsConstructor
@RestController
public class BibliothequeController {
    BibliothequeService bibliothequeService;

@PostMapping("/addbibliotheque")
Bibliotheque addBibliotheque(@RequestBody Bibliotheque bibliotheque){

    return bibliothequeService.addBibliotheque(bibliotheque);
}
    @PostMapping("/bibliotheque/{id}")
    Bibliotheque retrieveBibliotheque(@PathVariable Long id){

        return bibliothequeService.getBibliotheque(id);
    }
    @PostMapping("/bibliotheque")
    List<Bibliotheque> retrieveBibliotheques(){

        return bibliothequeService.getAllBibliotheque();
    }
    @DeleteMapping("/bibliotheque/{id}")
    void deleteBibliotheque(@PathVariable Long id){

        bibliothequeService.deleteBibliotheque(id);
    }
    @PutMapping("/bibliotheque")
    Bibliotheque updateBibliotheque(@RequestBody Bibliotheque bibliotheque){

        return bibliothequeService.updateBibliotheque(bibliotheque);
    }

    @PutMapping("/bloc/aff/{nombibliotheque}/{nomFoyer}")
    public Bibliotheque affecterBlocAFoyer(@PathVariable String nombibliotheque,@PathVariable String nomFoyer){

        return  bibliothequeService.affecterBibliothequeAFoyer(nombibliotheque,nomFoyer);
    }


}