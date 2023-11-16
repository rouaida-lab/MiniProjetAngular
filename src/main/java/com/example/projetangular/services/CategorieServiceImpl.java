package com.example.projetangular.services;

import com.example.projetangular.entities.Categorie;
import com.example.projetangular.entities.Livre;
import com.example.projetangular.repositories.CategorieRepository;
import com.example.projetangular.repositories.LivreRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements ICategorieService {


    CategorieRepository categorieRepository;

    LivreRepository livreRepository ;



    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Autowired

    public CategorieServiceImpl(CategorieRepository categorieRepository, LivreRepository livreRepository) {
        this.categorieRepository = categorieRepository;
        this.livreRepository = livreRepository;
    }

    @Override
    public Categorie addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie) ;
    }

    @Override
    public Categorie getCategorie(long idCategorie) {
        return categorieRepository.findById(idCategorie).orElse(null);
    }

    @Override
    public List<Categorie> getAllCategorie() {
        return categorieRepository.findAll();
    }

    @Override
    public void deleteCategorie(long idCategorie) {
        categorieRepository.deleteById(idCategorie);
    }

    @Override
    public Categorie updateCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie getCategorieDuLivre(long idLivre) {
        Livre livre = livreRepository.findById(idLivre).orElse(null);
        if (livre != null) {
            return livre.getCategorie();
        } else {
            return null;
        }
    }


}
