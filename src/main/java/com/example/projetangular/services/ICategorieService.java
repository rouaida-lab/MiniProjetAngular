package com.example.projetangular.services;

import com.example.projetangular.entities.Categorie;
import com.example.projetangular.entities.Livre;

import java.util.List;

public interface ICategorieService {

    Categorie addCategorie(Categorie categorie);

    Categorie getCategorie(long idCategorie);

    List<Categorie> getAllCategorie();

    void deleteCategorie(long idCategorie);

    Categorie updateCategorie(Categorie categorie);
}
