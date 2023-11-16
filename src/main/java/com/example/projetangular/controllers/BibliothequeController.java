package com.example.projetangular.controllers;

import com.example.projetangular.entities.Bibliotheque;
import com.example.projetangular.services.IBibliothequeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")

@AllArgsConstructor
@RestController
@RequestMapping("bibliotheques")


public class BibliothequeController {
    IBibliothequeService bibliothequeService;

@PostMapping("/addbibliotheque")
Bibliotheque addBibliotheque(@RequestBody Bibliotheque bibliotheque){

    return bibliothequeService.addBibliotheque(bibliotheque);
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
    @PutMapping("")
    Bibliotheque updateBibliotheque(@RequestBody Bibliotheque bibliotheque){

        return bibliothequeService.updateBibliotheque(bibliotheque);
    }

    @PutMapping("/aff/{nombibliotheque}/{nomFoyer}")
    public Bibliotheque affecterBibliothequeAFoyer(@PathVariable String nombibliotheque,@PathVariable String nomFoyer){

        return  bibliothequeService.affecterBibliothequeAFoyer(nombibliotheque,nomFoyer);
    }


}