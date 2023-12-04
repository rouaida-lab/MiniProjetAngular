package com.example.projetangular.services;

import com.example.projetangular.entities.Chambre;
import com.example.projetangular.entities.Reservation;
import com.example.projetangular.entities.TypeChambre;
import com.example.projetangular.entities.Utilisateur;
import com.example.projetangular.repositories.ChambreRepository;
import com.example.projetangular.repositories.ReservationRepository;
import com.example.projetangular.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImp implements IReservationService {

    ReservationRepository reservationRepository;
    UtilisateurRepository utilisateurRepository;
    ChambreRepository chambreRepository;


    @Override
    public Reservation ajouterReservation(Reservation reservation) {
        return null;
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
@Override
    public Reservation reserverChambre(Long chambreId,String emailEtudiant) {
        LocalDate currentDate = LocalDate.now();
        Utilisateur e =utilisateurRepository.findByEmail(emailEtudiant).get();
        Chambre chambre = chambreRepository.findChambreByIdChambre(chambreId);
        Date date =  Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        if(!reservationRepository.existsReservation(date,chambreId,false) ) {
            if (reservationRepository.existsReservation(date, chambreId, true) && chambre.getTypeChambre() != TypeChambre.SIMPLE) {
                Reservation reservation = reservationRepository.findReservationDisponible(date, chambreId);
                reservation.getEtudiants().add(e);
                if (chambre.getTypeChambre() == TypeChambre.DOUBLE || (chambre.getTypeChambre() == TypeChambre.TRIPLE && reservation.getEtudiants().size() == 3)) {
                    reservation.setEstValide(false);
                }
                chambre.getReservations().add(reservation);
                e.getReservations().add(reservation);
                utilisateurRepository.save(e);
                chambreRepository.save(chambre);
                return reservationRepository.save(reservation);

            } else {
                Reservation reservation = new Reservation();
                reservation.setChambre(chambre);
                reservation.getEtudiants().add(e);
                if (chambre.getTypeChambre() == TypeChambre.SIMPLE) {
                    reservation.setEstValide(false);
                }

                chambre.getReservations().add(reservation);
                e.getReservations().add(reservation);
                utilisateurRepository.save(e);
                chambreRepository.save(chambre);
                return reservationRepository.save(reservation);

            }
        }else{
            throw new IllegalArgumentException("La réservation existe déjà pour cette chambre à cette date.");
        }


    }


}
