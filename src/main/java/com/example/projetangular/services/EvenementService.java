package com.example.projetangular.services;

import com.example.projetangular.entities.Evenement;

import java.util.List;

public interface EvenementService {
    Evenement addEvenement(Evenement evenement);

    Evenement getEvenement(long idevenement);

    List<Evenement> getAllEvenement();

    void deleteEvenement(long idevenement);

    Evenement updateEvenement(Evenement evenement);
    public Evenement affecterEvenementABibliotheque( String nomEvenement, String nomBibliotheque) ;
}