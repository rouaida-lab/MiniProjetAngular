package com.example.projetangular.services;

import com.example.projetangular.entities.EmpruntLivre;

import java.util.List;

public interface IEmpruntLivreService {

    EmpruntLivre addEmpruntLivre(EmpruntLivre empruntLivre);

    EmpruntLivre getEmpruntLivre(long idEmpruntLivre);

    List<EmpruntLivre> getAllEmpruntLivres();

    void deleteEmpruntLivre(long idEmpruntLivre);

    EmpruntLivre updateEmpruntLivre(EmpruntLivre empruntLivre);

    EmpruntLivre accepterEmpruntLivre(long idEmpruntLivre);

    void refuserEmpruntLivre(long idEmpruntLivre);


}
