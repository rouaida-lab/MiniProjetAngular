package com.example.projetangular.services;

import com.example.projetangular.entities.Evenement;

import java.util.List;

public interface IEvenementService {
    Evenement addEvenement(Evenement evenement);

    Evenement getEvenement(long idevenement);

    List<Evenement> getAllEvenement();

    void deleteEvenement(long idevenement);

    Evenement updateEvenement(long idEvenement ,Evenement evenement);
    public Evenement affecterEvenementABibliotheque( String nomE, String nomB) ;
}