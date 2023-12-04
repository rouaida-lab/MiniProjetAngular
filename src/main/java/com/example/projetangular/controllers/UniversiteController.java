package com.example.projetangular.controllers;

import com.example.projetangular.entities.Departement;
import com.example.projetangular.entities.Universite;
import com.example.projetangular.services.IDepartementService;
import com.example.projetangular.services.IUniversiteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Universite")
public class UniversiteController {

    IUniversiteService universiteService;
    IDepartementService departementService;

    @PostMapping("/affecterFU/{idFoyer}/{nomUniversite}")
    public Universite affecterFoyerAUniversite(@PathVariable long idFoyer, @PathVariable String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }

    @PostMapping("/add")
    public Universite addUniversite(@RequestBody Universite universite) {
        return universiteService.addUniversite(universite);
    }

    @GetMapping("/getUniversiteById/{id}")
    public Universite retrieveUniversite(@PathVariable Long id) {
        return universiteService.getUniversite(id);
    }

    @GetMapping("/All")
    public List<Universite> retrieveUniversites() {
        return universiteService.getAllUniversites();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        universiteService.deleteUniversite(id);
    }

    @PutMapping("/update/{id}")
    public Universite updateUniversite(
            @PathVariable Long id,
            @RequestParam("nomUniversite") String nomUniversite,
            @RequestParam("adresse") String adresse,
            @RequestParam("etatUniversite") String etatUniversite) {

        Universite universite = universiteService.getUniversite(id);
        universite.setNomUniversite(nomUniversite);
        universite.setAdresse(adresse);
        universite.setEtatUniversite(etatUniversite);

        // Mettez à jour les autres champs si nécessaire

        return universiteService.updateUniversite(universite);
    }

    @PutMapping("/universite/{idFoyecr}/{idUniversite}")
    public Universite affecterFoyerAUniversite(@PathVariable long idFoyer, @PathVariable long idUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, idUniversite);
    }

    @PutMapping("/affecterDepartementAUniversite/{departementId}/{universiteId}")
    public Departement affecterDepartementAUniversite(
            @PathVariable long departementId,
            @PathVariable long universiteId
    ) {
        return departementService.affecterDepartementAUniversite(departementId, universiteId);
    }

    @GetMapping("/getDepartementsByNomUniversite/{nomUniversite}")
    public List<Departement> getDepartementsByNomUniversite(@PathVariable String nomUniversite) {
        return universiteService.getDepartementsByNomUniversite(nomUniversite);
    }

}