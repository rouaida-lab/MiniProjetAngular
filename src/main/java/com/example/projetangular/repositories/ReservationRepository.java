package com.example.projetangular.repositories;

import com.example.projetangular.entities.Reservation;
import com.example.projetangular.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation,Long> {
    List<Reservation> findByAnneeUniversitaireBetween(Date startDate, Date endDate);
    List<Reservation>findByAnneeUniversitaireBefore(Date date);
    List<Reservation>findByAnneeUniversitaireBetweenAndEstValide(Date startDate, Date endDate,Boolean estValide);
    //List<Reservation>findByEstValide(Boolean estValide);
    @Query("SELECT r FROM Reservation r JOIN Chambre c ON r MEMBER OF c.reservations " +
            "WHERE r.anneeUniversitaire = ?1 AND c.idChambre = ?2 AND r.estValide = true")
    Reservation findReservationDisponible(Date anneUni, Long idChambre);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reservation r JOIN Chambre c ON r MEMBER OF c.reservations " +
            "WHERE r.anneeUniversitaire = ?1 AND c.idChambre = ?2 AND r.estValide = ?3")
    boolean existsReservation(Date anneUni, Long idChambre,Boolean valide);
    public List<Reservation> findFoyerByAnneeUniversitaire(Date annee_universitaire);
    void deleteByAnneeUniversitaire(Date annee_universitaire);
    Reservation findReservationByIdReservation(Long idReservation);
    @Query("SELECT r FROM Reservation r JOIN r.etudiants e WHERE e = :etudiant")
    List<Reservation> findReservationsByEtudiant(@Param("etudiant") Utilisateur utilisateur);

}
