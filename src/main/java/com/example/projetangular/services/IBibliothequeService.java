package com.example.projetangular.services;

import com.example.projetangular.entities.Bibliotheque;
import com.example.projetangular.entities.Foyer;
import org.springframework.stereotype.Service;
import  com.example.projetangular.repositories.BibliothequeRepository ;
import  com.example.projetangular.repositories.ChambreRepository;
import   com.example.projetangular.repositories.FoyerRepository;

import java.util.List;

@Service
public class IBibliothequeService implements BibliothequeService {
   BibliothequeRepository bibliothequeRepository ;
   ChambreRepository chambreRepository;
    FoyerRepository foyerRepository;


    public IBibliothequeService(BibliothequeRepository bibliothequeRepository) {

        this.bibliothequeRepository = bibliothequeRepository;
    }






    @Override
    public Bibliotheque addBibliotheque(Bibliotheque bibliotheque) {
        bibliothequeRepository.save(bibliotheque);
        return bibliotheque ;
    }

    @Override
    public Bibliotheque getBibliotheque(long idBibliotheque) {
        return bibliothequeRepository.findById(idBibliotheque).orElse(null);
    }

    @Override
    public List<Bibliotheque> getAllBibliotheque() {
        return bibliothequeRepository.findAll();
    }

    @Override
    public void deleteBibliotheque(long idBibliotheque) {
        bibliothequeRepository.deleteById(idBibliotheque);
    }

  public Bibliotheque updateBibliotheque(Bibliotheque bibliotheque) {
        return bibliothequeRepository.save(bibliotheque) ;
  }

    @Override
    public Bibliotheque affecterBibliothequeAFoyer(String nomBibliotheque, String nomFoyer) {
        Bibliotheque bibliotheque = bibliothequeRepository.findByNomBibliotheque(nomBibliotheque);
        Foyer foyer = foyerRepository.findByNomFoyer(nomFoyer);
        bibliotheque.setFoyer(foyer);
        return bibliothequeRepository.save(bibliotheque);

}
}
