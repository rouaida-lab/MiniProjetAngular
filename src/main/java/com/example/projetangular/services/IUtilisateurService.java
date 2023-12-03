package com.example.projetangular.services;


import com.example.projetangular.entities.RoleUtilisateur;
import com.example.projetangular.entities.Universite;
import com.example.projetangular.entities.Utilisateur;

import java.util.List;
import java.util.Map;

public interface IUtilisateurService {
    Utilisateur addUtilisateur(Utilisateur utilisateur);
    Utilisateur login(String email,String password);
    List<Utilisateur> getUtilisateurs();
    List<Utilisateur> getEtudiantsByEtatNon(RoleUtilisateur role ,String etat);

    Utilisateur getUtilisateur(long id);
    Utilisateur getUtilisateurByEmail(String email);

    boolean deleteUtilisateur(long idUtilisateur);
    Utilisateur updateUtilisateur(long id,Utilisateur u);
    public Utilisateur affecterReservationToEtudiant(long idEtudiant, String idReservation);
    boolean isEmailAlreadyExists(String email);
     Utilisateur changerEtatUtilisateur(long id,String etat);

    Map<Universite, List<Utilisateur>> getEtudiantsByUniversite();

    boolean confirmUserAccount(String token);
    Utilisateur getUtilisateurByEmprunt(long idEmprunt);

    String getEmailUtilisateur(long id);
    }
