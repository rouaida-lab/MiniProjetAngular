package com.example.projetangular.services;

import com.example.projetangular.entities.Chambre;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IChambreService {
   /*Chambre addChambre(Chambre chambre);
    List<Chambre> getAllChambres();
    Chambre getChambre(Long idChambre);
    void deleteChambre(Long idChambre);
    Chambre updateChambre(Chambre chambre);

*/


    public Chambre ajouterChambre(Chambre chambre);

    public List<Chambre> getBlocByNumeroChambre(Long numeroChambre);

    List<Chambre> getAllChambre();

    public void supprimerChambreParNumeroChambre(Long numeroChambre);

    public void supprimerChambreParIdChambre(Long idChambre);

    Chambre mettreAJourChambre(Chambre chambre);

    public List<Chambre> chambreByUniversite(Long idUniversite);


}