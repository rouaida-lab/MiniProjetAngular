package com.example.projetangular.services;

import com.example.projetangular.entities.Categorie;
import com.example.projetangular.entities.Livre;

import java.util.List;

public interface ILivreService {

    Livre addLivre(Livre livre);

    Livre getLivre(long idLivre);

    List<Livre> getAllLivre();

    void deleteLivre(long idLivre);

    Livre updateLivre(Livre livre);

}
