package com.example.projetangular.controllers;

import com.example.projetangular.entities.Universite;
import com.example.projetangular.service.IUniversiteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteController {

    IUniversiteService universiteService;


    @PostMapping("/add")
    public Universite addUniversite(@RequestBody Universite universite) {
        return universiteService.addUniversite(universite);
    }

    @GetMapping("/{id}")
    public Universite retrieveUniversite(@PathVariable Long id) {
        return universiteService.getUniversite(id);
    }

    @GetMapping
    public List<Universite> retrieveUniversites() {
        return universiteService.getAllUniversites();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        universiteService.deleteUniversite(id);
    }

    @PutMapping("/update")
    public Universite updateUniversite(@RequestBody Universite universite) {
        return universiteService.updateUniversite(universite);
    }

    @PutMapping("/universite/{idFoyer}/{idUniversite}")
    public Universite affecterFoyerAUniversite(@PathVariable long idFoyer, @PathVariable long idUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, idUniversite);
    }
}