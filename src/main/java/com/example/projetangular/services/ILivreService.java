package com.example.projetangular.services;

import com.example.projetangular.entities.Livre;

import java.util.List;

public interface ILivreService {
    List<Livre> getAllLivre();

    Livre getLivre(long idLivre);
}
