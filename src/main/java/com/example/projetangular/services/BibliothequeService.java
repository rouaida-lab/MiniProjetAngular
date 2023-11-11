package com.example.projetangular.services;

import com.example.projetangular.entities.Bibliotheque;

import java.util.List;

public interface BibliothequeService {
    Bibliotheque addBibliotheque(Bibliotheque bibliotheque);

    Bibliotheque getBibliotheque(long idBibliotheque);

    List<Bibliotheque> getAllBibliotheque();

    void deleteBibliotheque(long idBibliotheque);

    Bibliotheque updateBibliotheque(Bibliotheque bibliotheque);
    public Bibliotheque affecterBibliothequeAFoyer( String nomBibliotheque, String nomFoyer) ;
}