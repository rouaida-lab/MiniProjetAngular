package com.example.projetangular.repositories;

import com.example.projetangular.entities.Bibliotheque;
import com.example.projetangular.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Evenement,Long> {
    Evenement findByNomEvenement(String nom);

}
