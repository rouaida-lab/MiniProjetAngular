package com.example.projetangular.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.example.projetangular.entities.Departement;
import com.example.projetangular.repositories.DepartementRepository;
import com.example.projetangular.repositories.UniversiteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartementService implements  IDepartementService{

    @Autowired
    DepartementRepository departementRepository;

    @Autowired
    UniversiteRepository universiteRepository;

    @Override
    public Departement addDepartment(Departement departement) {
        log.info("universite ajouté :\n" +
                "Nom d\'Departement "+departement.getIdDepartement() +" " +
                "ID d\'Departement " +departement.getNomDepartement()+"" );
       return  departementRepository.save(departement);

    }


    @Override
    public Departement updateDepartment(Departement departement) {
        return departementRepository.save(departement);

    }

    @Override
    public void deleteDepartment(Long id) {
        log.info("suppression d'Une departements par id");
        departementRepository.deleteById(id);
    }


    @Override
    public List<Departement> getAllDepartements() {
        log.info("récuperation de tous les departement");

        return departementRepository.findAll();
    }

    @Override
    public Departement getDepartement(Long id) {
        return departementRepository.findById(id).orElse(null);
    }
}
