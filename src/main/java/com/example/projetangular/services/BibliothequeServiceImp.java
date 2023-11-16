package com.example.projetangular.services;

import com.example.projetangular.entities.Bibliotheque;
import com.example.projetangular.entities.Foyer;
import org.springframework.stereotype.Service;
import  com.example.projetangular.repositories.BibliothequeRepository ;
import   com.example.projetangular.repositories.FoyerRepository;

import java.util.List;

@Service
public class BibliothequeServiceImp implements IBibliothequeService {
   BibliothequeRepository bibliothequeRepository ;
    FoyerRepository foyerRepository ;



    public BibliothequeServiceImp(BibliothequeRepository bibliothequeRepository) {

        this.bibliothequeRepository = bibliothequeRepository;
    }






    @Override
    public Bibliotheque addBibliotheque(Bibliotheque bibliotheque) {
      return  bibliothequeRepository.save(bibliotheque);
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

  public Bibliotheque updateBibliotheque(long idBibliotheque , Bibliotheque bibliotheque) {
        bibliotheque.setIdBibliotheque(idBibliotheque);
        return bibliothequeRepository.save(bibliotheque) ;
  }

    @Override
    public Bibliotheque affecterBibliothequeAFoyer(String nomB, String nomFoyer) {
        Bibliotheque bibliotheque = bibliothequeRepository.findByNomB(nomB);
        Foyer foyer = foyerRepository.findByNomFoyer(nomFoyer);
        bibliotheque.setFoyer(foyer);
        return bibliothequeRepository.save(bibliotheque);

}
}
