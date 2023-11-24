package com.example.projetangular.services;

import com.example.projetangular.entities.Utilisateur;
import jdk.jshell.execution.Util;

public interface IUtilisateurService {
    Utilisateur getUtilisateurByEmail(String email);

    Utilisateur getUtilisateurByEmprunt(long idEmprunt);

    String getEmailUtilisateur(long id);
}
