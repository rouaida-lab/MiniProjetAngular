package com.example.projetangular.service;

import com.example.projetangular.entities.Universite;

import java.util.List;

public interface IUniversiteService {
    Universite addUniversite(Universite universite);

    Universite getUniversite(Long IdUniversite);

    List<Universite> getAllUniversites();

    void deleteUniversite (Long IdUniversite);

    Universite updateUniversite(Universite universite);


    Universite affecterFoyerAUniversite(long idFoyer , long idUniversite);
    Universite affecterFoyerAUniversite(long idFoyer , String nomUniversite);

    Universite desaffecterFoyerAUniversite(long idFoyer , long idUniversite);

}
