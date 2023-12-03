package com.example.projetangular.services;

import com.example.projetangular.entities.Reservation;
import com.example.projetangular.entities.Utilisateur;
import com.example.projetangular.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImp implements IReservationService {


    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation ajouterReservation(Reservation reservation  ) {

        Reservation savedReservation = reservationRepository.save(reservation);
        return savedReservation;
    }




    @Override
    public List<Reservation> getReservationByAnneeUniversitaire(Date annee_universitaire) {
        return reservationRepository.findFoyerByAnneeUniversitaire(annee_universitaire);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    @Override
    public void supprimerReservationParAnneeUniversitaire(Date annee_universitaire) {
        reservationRepository.deleteByAnneeUniversitaire(annee_universitaire);
    }

    @Override
    public void supprimerReservationParIdReservation(Long idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    @Override
    public Reservation mettreAJourReservation(Reservation res) {
        Reservation existingReservation = reservationRepository.findReservationByIdReservation(res.getIdReservation());

        if (existingReservation != null) {

            return reservationRepository.save(res);
        } else {

            return null;
        }
    }

    @Override
    public List<Reservation> getReservationsByEtudiant(Utilisateur etudiant) {
        return reservationRepository.findReservationsByEtudiant(etudiant);

    }
}
