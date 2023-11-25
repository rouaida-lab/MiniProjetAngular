package com.example.projetangular.services;

import com.example.projetangular.entities.Chambre;
import com.example.projetangular.repositories.ChambreRepository;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChambreServiceImp implements IChambreService{


    @Autowired
    ChambreRepository chambreRepository;

    @Override
    public Chambre ajouterChambre(Chambre chambre) {
        /*chambre.setNumeroChambre(10L);
        chambre.setTypeC(SIMPLE);*/
        chambreRepository.save(chambre);
        return chambre;
    }

    @Override
    public List<Chambre> getBlocByNumeroChambre(Long numeroChambre) {
        return chambreRepository.findBlocByNumeroChambre(numeroChambre);
    }

    @Override
    public List<Chambre> getAllChambre() {
        return (List<Chambre>) chambreRepository.findAll();
    }

    @Override
    public void supprimerChambreParNumeroChambre(Long numeroChambre) {
        chambreRepository.deleteByNumeroChambre(numeroChambre);
    }

    @Override
    public void supprimerChambreParIdChambre(Long idChambre) {
        chambreRepository.deleteById(idChambre);
    }

    @Override
    public Chambre mettreAJourChambre(Chambre chambre) {

       /* Chambre existingChambre = chambreRepository.findChambreByIdChambre(chambre.getIdChambre());


        if (existingChambre != null) {

            return chambreRepository.save(chambre);
        } else {

            return null;
        }*/
        return chambreRepository.save(chambre);
    }

    @Override
    public List <Chambre> chambreByUniversite(Long idUniversite) {
        return chambreRepository.getChambreByUniversite(idUniversite);
    }



}
