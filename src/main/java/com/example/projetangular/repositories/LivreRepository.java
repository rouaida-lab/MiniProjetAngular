package com.example.projetangular.repositories;

import com.example.projetangular.entities.Categorie;
import com.example.projetangular.entities.Livre;
import com.example.projetangular.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LivreRepository extends JpaRepository<Livre,Long> {
    List<Livre> findAllByCategorie(Categorie categorie);


    @Query("SELECT l FROM Livre l JOIN l.emprunts e WHERE e.idEmprunt = :idEmprunt")
    Livre findByEmpruntId(@Param("idEmprunt") Long idEmprunt);
}
