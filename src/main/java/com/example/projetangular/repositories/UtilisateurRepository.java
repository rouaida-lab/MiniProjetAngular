package com.example.projetangular.repositories;

import com.example.projetangular.entities.RoleUtilisateur;
import com.example.projetangular.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Utilisateur findByCin(long cin);
    Optional<Utilisateur> findByEmail(String email);
  //  boolean existsByEmail(String email);
    Utilisateur findByToken(String token);

    List<Utilisateur> findByRoleAndEtatIsNot(RoleUtilisateur role,String etat);

    @Query("SELECT u FROM Utilisateur u JOIN u.emprunts e WHERE e.idEmprunt = :idEmprunt")
    Utilisateur findByEmpruntId(@Param("idEmprunt") Long idEmprunt);


    @Query("SELECT u.email FROM Utilisateur u where u.idEtudiant = :id")
    String findEmailByUserId(@Param("id") Long id);
}
