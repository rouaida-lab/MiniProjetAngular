package com.example.projetangular.services;

import com.example.projetangular.entities.Bibliotheque;
import com.example.projetangular.entities.Foyer;

import java.util.List;

public interface IBibliothequeService {
    Bibliotheque addBibliotheque(Bibliotheque bibliotheque);

    Bibliotheque getBibliotheque(long idBibliotheque);

    List<Bibliotheque> getAllBibliotheque();

    void deleteBibliotheque(long idBibliotheque);
    public Bibliotheque getBibliothequeByFoyer(Foyer foyer) ;

    Bibliotheque updateBibliotheque(long idBibliotheque ,Bibliotheque bibliotheque);
    public Bibliotheque affecterBibliothequeAFoyer( String nomB, String nomFoyer) ;
}