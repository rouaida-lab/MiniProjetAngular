package com.example.projetangular.repositories;

import com.example.projetangular.entities.RoleUtilisateur;
import com.example.projetangular.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Utilisateur findByCin(long cin);
    Optional<Utilisateur> findByEmail(String email);
  //  boolean existsByEmail(String email);
    Utilisateur findByToken(String token);

    List<Utilisateur> findByRoleAndEtatIsNot(RoleUtilisateur role,String etat);
}
