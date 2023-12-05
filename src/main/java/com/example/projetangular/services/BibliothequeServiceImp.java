package com.example.projetangular.services;

import com.example.projetangular.entities.Bibliotheque;
import com.example.projetangular.entities.Evenement;
import com.example.projetangular.entities.Foyer;
import com.example.projetangular.entities.Livre;
import com.example.projetangular.repositories.EvenementRepository;
import com.example.projetangular.repositories.LivreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import  com.example.projetangular.repositories.BibliothequeRepository ;
import   com.example.projetangular.repositories.FoyerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BibliothequeServiceImp implements IBibliothequeService {
   BibliothequeRepository bibliothequeRepository ;
    FoyerRepository foyerRepository ;
    EvenementRepository evenementRepository ;

    LivreRepository livreRepository;



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
    public Bibliotheque getBibliothequeByEvenement(long idEvenement) {
        Evenement evenement = evenementRepository.findById(idEvenement).orElse(null);
        if (evenement != null) {
            return evenement.getBibliotheque();
        } else {
            return null;
        }
    }


    @Override
    public Bibliotheque getBibliothequeByFoyer(Foyer foyer) {
        return bibliothequeRepository.findByFoyer(foyer);
    }
    @Override
    public Bibliotheque affecterBibliothequeAFoyer(String nomB, String nomFoyer) {
        Bibliotheque bibliotheque = bibliothequeRepository.findByNomB(nomB);
        Foyer foyer = foyerRepository.findByNomFoyer(nomFoyer);
        bibliotheque.setFoyer(foyer);
        return bibliothequeRepository.save(bibliotheque);

}

    @Override
    public Bibliotheque ajouterLivreBiblio(Livre livre,Bibliotheque bibliotheque) {
        bibliotheque.getLivres().add(livre);
        return bibliothequeRepository.save(bibliotheque) ;
    }
}
