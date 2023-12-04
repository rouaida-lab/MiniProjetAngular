package com.example.projetangular.controllers;


import com.example.projetangular.entities.Departement;
import com.example.projetangular.entities.Universite;
import com.example.projetangular.services.IDepartementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("Departement")
public class DepartementController {

    @Autowired
    IDepartementService ds;


    @PostMapping("/addDepartment")
    public Departement addDepartment(@RequestBody Departement d) {
        return ds.addDepartment(d);
    }


    @DeleteMapping("/deleteDepartment/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        System.out.println("ID du département à supprimer : " + id);
        ds.deleteDepartment(id);
    }

    @GetMapping("/getAllDepartments")
    public List<Departement> getAllDepartments() {
        return ds.getAllDepartements();
    }

    @GetMapping("/getDepartementById/{id}")
    public Departement getDepartementById(@PathVariable Long id) {
        return ds.getDepartement(id);
    }

    @GetMapping("/getUniversites")
    public List<Universite> getUniversites() {
        return ds.getUniversites();
    }

    @PutMapping("/update/{id}")
    public Departement updateDepartement(
            @PathVariable Long id,
            @RequestParam("nomDepartement") String nomDepartement,
            @RequestParam("responsable") String responsable,
            @RequestParam("nombreProfesseurs") int nombreProfesseurs,
            @RequestParam("specialite") String specialite) {

        Departement departement = ds.getDepartement(id);
        departement.setNomDepartement(nomDepartement);
        departement.setResponsable(responsable);
        departement.setNombreProfesseurs(nombreProfesseurs);
        departement.setSpecialite(specialite);

        return ds.updateDepartment(departement);    }

    @PutMapping("/affecterDepartementAUniversite/{departementId}/{universiteId}")
    public Departement affecterDepartementAUniversite(
            @PathVariable long departementId,
            @PathVariable long universiteId
    ) {
        return ds.affecterDepartementAUniversite(departementId, universiteId);
    }
}