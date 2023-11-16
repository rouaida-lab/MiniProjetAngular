package com.example.projetangular.services;


import com.example.projetangular.entities.Universite;
import com.example.projetangular.entities.Utilisateur;

import java.util.List;
import java.util.Map;

public interface IUtilisateurService {
    Utilisateur addUtilisateur(Utilisateur utilisateur);
    Utilisateur login(String email,String password);
    List<Utilisateur> getUtilisateurs();
    Utilisateur getUtilisateur(long id);

    boolean deleteUtilisateur(long idUtilisateur);
    Utilisateur updateUtilisateur(long id,Utilisateur u);
    public Utilisateur affecterReservationToEtudiant(long idEtudiant, String idReservation);
    boolean isEmailAlreadyExists(String email);
     Utilisateur changerEtatUtilisateur(long id,String etat);

    Map<Universite, List<Utilisateur>> getEtudiantsByUniversite();

    }
