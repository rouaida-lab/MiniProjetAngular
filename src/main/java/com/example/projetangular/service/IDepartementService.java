package com.example.projetangular.service;



import com.example.projetangular.entities.Departement;
import com.example.projetangular.entities.Universite;

import java.util.List;

public interface IDepartementService {
    Departement addDepartment(Departement departement);

    Departement updateDepartment ( Departement departement);
    void deleteDepartment(Long id);

    List<Departement> getAllDepartements();


    Departement getDepartement(Long id);

    List<Universite> getUniversites();

    Departement affecterDepartementAUniversite(Long departementId, Long universiteId);



    }
