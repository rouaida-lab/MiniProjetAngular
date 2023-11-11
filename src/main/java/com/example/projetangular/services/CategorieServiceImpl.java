package com.example.projetangular.services;

import com.example.projetangular.entities.Categorie;
import com.example.projetangular.repositories.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements ICategorieService {


    CategorieRepository categorieRepository;
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
}
