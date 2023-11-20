package com.example.projetangular.services;

import com.example.projetangular.entities.Utilisateur;

public interface IUtilisateurService {
    Utilisateur getUtilisateurByEmail(String email);

    Utilisateur getUtilisateurByEmprunt(long idEmprunt);
}
