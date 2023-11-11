package com.example.projetangular.repositories;

import com.example.projetangular.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Utilisateur findByCin(long cin);
}
