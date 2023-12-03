package com.example.projetangular.service;

import com.example.projetangular.entities.Foyer;
import com.example.projetangular.entities.Universite;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
public class DepartementService implements  IDepartementService {

    @Autowired
    DepartementRepository departementRepository;

    @Autowired
    UniversiteRepository universiteRepository;

    @Override
    public Departement addDepartment(Departement departement) {
        log.info("universite ajouté :\n" +
                "Nom d\'Departement " + departement.getIdDepartement() + " " +
                "ID d\'Departement " + departement.getNomDepartement() + "");
        return departementRepository.save(departement);

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

    @Override
    public List<Universite> getUniversites() {
        log.info("Fetching all universities");
        return universiteRepository.findAll();
    }

    @Override
    public Departement affecterDepartementAUniversite(Long departementId, Long universiteId) {
        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new EntityNotFoundException("Departement not found with id: " + departementId));

        Universite universite = universiteRepository.findById(universiteId)
                .orElseThrow(() -> new EntityNotFoundException("Universite not found with id: " + universiteId));

        departement.setUniversite(universite);
        Departement updatedDepartement = departementRepository.save(departement);

        // Rafraîchir l'université pour inclure la liste mise à jour des départements
        universite.getDepartements().add(updatedDepartement);

        return updatedDepartement;
    }

}