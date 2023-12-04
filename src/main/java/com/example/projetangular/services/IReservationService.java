package com.example.projetangular.services;

import com.example.projetangular.entities.Chambre;
import com.example.projetangular.entities.Reservation;
import com.example.projetangular.entities.Utilisateur;
import jdk.jshell.execution.Util;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface IReservationService   {/*
    Reservation addReservation(Reservation reservation);
    List<Reservation> getAllReservation();
    Reservation getReservation(Long idReservation);
    void deleteReservation(Long idReservation);
    Reservation updateReservation(Reservation reservation);

*/


    public Reservation ajouterReservation(Reservation reservation);
    public List<Reservation> getReservationByAnneeUniversitaire(Date anneeUniversitaire);
    List<Reservation> getAllReservations();

    void supprimerReservationParAnneeUniversitaire(Date anneeUniversitaire);
    void supprimerReservationParIdReservation(Long idReservation);
    Reservation mettreAJourReservation(Reservation res);

    public List<Reservation> getReservationsByEtudiant(Utilisateur utilisateur);
}
