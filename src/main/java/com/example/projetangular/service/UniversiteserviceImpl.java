package com.example.projetangular.service;


import com.example.projetangular.entities.Departement;
import com.example.projetangular.entities.Foyer;
import com.example.projetangular.entities.Universite;
import com.example.projetangular.repositories.DepartementRepository;
import com.example.projetangular.repositories.FoyerRepository;
import com.example.projetangular.repositories.UniversiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversiteserviceImpl implements IUniversiteService{


    @Autowired
    UniversiteRepository universiteRepository;
    @Autowired
    FoyerRepository foyerRepository;
    @Autowired
    DepartementRepository departementRepository;

    public UniversiteserviceImpl(UniversiteRepository universiteRepository){
        this.universiteRepository=universiteRepository;
    }

    @Override
    public Universite addUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public Universite getUniversite(Long IdUniversite) {
        return universiteRepository.findById(IdUniversite).orElse(null);
    }

    @Override
    public List<Universite> getAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public void deleteUniversite(Long IdUniversite) {
        universiteRepository.deleteById(IdUniversite);

    }

    @Override
    public Universite updateUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, long idUniversite) {
        Universite universite= universiteRepository.findById(idUniversite).orElse(null);
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        universite.setFoyer(foyer);
        return universiteRepository.save(universite);
    }


    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        Universite universite= universiteRepository.findByNomUniversite(nomUniversite);
        universite.setFoyer(foyer);
        return universiteRepository.save(universite);
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idFoyer, long idUniversite) {
        Universite universite= universiteRepository.findById(idUniversite).orElse(null);
        universite.setFoyer(null);
        return universiteRepository.save(universite);
    }


    @Override
    public List<Departement> getDepartementsByNomUniversite(String nomUniversite) {
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);

        if (universite != null) {
            return departementRepository.findByUniversite(universite);
        } else {
            // Gérer le cas où l'université n'est pas trouvée
            return null;
        }
    }
}
