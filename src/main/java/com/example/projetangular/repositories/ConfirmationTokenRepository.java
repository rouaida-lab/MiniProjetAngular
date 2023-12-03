package com.example.projetangular.repositories;

import com.example.projetangular.entities.ConfirmationToken;
import com.example.projetangular.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
    ConfirmationToken findByToken(String token);
}
