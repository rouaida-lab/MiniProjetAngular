package com.example.projetangular.services;

import com.example.projetangular.entities.Reclamation;

import java.util.List;

public interface IReclamationService {
    Reclamation ajouterReclamation(Reclamation reclamation,String email);
    List<Reclamation>listeReclamationsParEtudiant(String email,String etat);
    List<Reclamation>listeReclamations(String etat);
    Reclamation modifierReclamation(Reclamation reclamation,long idRec);
    Reclamation changerEtatReclamation(Long id,String etat);

}
