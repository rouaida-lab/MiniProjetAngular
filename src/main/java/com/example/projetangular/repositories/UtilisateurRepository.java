package com.example.projetangular.repositories;

import com.example.projetangular.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Utilisateur findByEmail(String email);

    @Query("SELECT u FROM Utilisateur u JOIN u.emprunts e WHERE e.idEmprunt = :idEmprunt")
    Utilisateur findByEmpruntId(@Param("idEmprunt") Long idEmprunt);
}
