package com.example.projetangular.services;

import com.example.projetangular.entities.Evenement;
import com.example.projetangular.entities.Bibliotheque;
import com.example.projetangular.repositories.EvenementRepository;
import com.example.projetangular.repositories.ChambreRepository;
import com.example.projetangular.repositories.BibliothequeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvenementServiceImp implements IEvenementService {
   EvenementRepository evenementRepository ;
   ChambreRepository chambreRepository;
    BibliothequeRepository bibliothequeRepository;


    public EvenementServiceImp(EvenementRepository evenementRepository) {

        this.evenementRepository = evenementRepository;
    }






    @Override
    public Evenement addEvenement(Evenement evenement) {
        evenementRepository.save(evenement);
        return evenement ;
    }

    @Override
    public Evenement getEvenement(long idEvenement) {
        return evenementRepository.findById(idEvenement).orElse(null);
    }

    @Override
    public List<Evenement> getAllEvenement() {
        return evenementRepository.findAll();
    }

    @Override
    public void deleteEvenement(long idEvenement) {
        evenementRepository.deleteById(idEvenement);
    }

  public Evenement updateEvenement(Evenement evenement) {
        return evenementRepository.save(evenement) ;
  }

    @Override
    public Evenement affecterEvenementABibliotheque(String nomE, String nomB) {
        Evenement evenement = evenementRepository.findByNomE(nomE);
        Bibliotheque bibliotheque = bibliothequeRepository.findByNomB(nomB);
        evenement.setBibliotheque(bibliotheque);
        return evenementRepository.save(evenement);

}
}