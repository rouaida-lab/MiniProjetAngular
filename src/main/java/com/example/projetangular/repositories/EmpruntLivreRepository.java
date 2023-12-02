package com.example.projetangular.repositories;

import com.example.projetangular.entities.EmpruntLivre;
import com.example.projetangular.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EmpruntLivreRepository extends JpaRepository<EmpruntLivre,Long> {

    @Query("SELECT e FROM EmpruntLivre e WHERE e.etat = 'non valide'")
    List<EmpruntLivre> findEmpruntsLivreEnCours();


    @Query("SELECT e FROM EmpruntLivre e WHERE e.etat = 'valide'")
    List<EmpruntLivre> findEmpruntsLivreAccepte();




}
