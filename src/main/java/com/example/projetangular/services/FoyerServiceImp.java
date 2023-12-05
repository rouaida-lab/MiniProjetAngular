package com.example.projetangular.services;

import com.example.projetangular.entities.Bibliotheque;
import com.example.projetangular.entities.Evenement;
import com.example.projetangular.entities.Foyer;
import com.example.projetangular.repositories.BibliothequeRepository;
import com.example.projetangular.repositories.ChambreRepository;
import com.example.projetangular.repositories.EvenementRepository;
import com.example.projetangular.repositories.FoyerRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FoyerServiceImp implements IFoyerService {
    FoyerRepository foyerRepository ;
   ChambreRepository chambreRepository;
    BibliothequeRepository bibliothequeRepository;


    public FoyerServiceImp(FoyerRepository foyerRepository) {

        this.foyerRepository = foyerRepository;
    }
    @Override
    public Foyer getFoyer(long idFoyer) {
        return foyerRepository.findById(idFoyer).orElse(null);
    }


}
