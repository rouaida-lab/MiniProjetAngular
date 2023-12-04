package com.example.projetangular.repositories;

import com.example.projetangular.entities.Bibliotheque;
import com.example.projetangular.entities.Evenement;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EvenementRepository extends JpaRepository<Evenement,Long> {
    Evenement findByNomE(String nomE);
    List<Evenement> findAllByBibliotheque(Bibliotheque bibliotheque);

    @Transactional
    @Modifying
    @Query("UPDATE Evenement e SET e.dateDebut = :newDateDebut WHERE e.idEvenement = :eventId")
    void updateDateDebut(long eventId , Date newDateDebut);


}
