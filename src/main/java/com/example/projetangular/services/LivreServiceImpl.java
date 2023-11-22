package com.example.projetangular.services;

import com.example.projetangular.entities.Categorie;
import com.example.projetangular.entities.Livre;
import com.example.projetangular.repositories.CategorieRepository;
import com.example.projetangular.repositories.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreServiceImpl implements ILivreService{

    LivreRepository livreRepository;

    public LivreServiceImpl(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    @Override
    public Livre addLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    @Override
    public Livre getLivre(long idLivre) {
        return livreRepository.findById(idLivre).orElse(null);
    }


    @Override
    public List<Livre> getAllLivre() {
        return livreRepository.findAll();
    }


    @Override
    public List<Livre> getAllLivreByCategory(Categorie categorie) {
        return livreRepository.findAllByCategorie(categorie);
    }

    @Override
    public Livre getLivreByEmprunt(long idEmprunt) {
        return livreRepository.findByEmpruntId(idEmprunt);
    }

    @Override
    public void updateDisponibiliteLivre(Livre livre , boolean disp) {
        livre.setDisponibilite(disp);
        livreRepository.save(livre) ;
    }

    @Override
    public void deleteLivre(long idLivre) {
        livreRepository.deleteById(idLivre);
    }

    @Override
    public Livre updateLivre(Livre livre) {
        return livreRepository.save(livre);
    }



}
