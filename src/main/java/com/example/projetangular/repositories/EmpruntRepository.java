package com.example.projetangular.repositories;

import com.example.projetangular.entities.EmpruntLivre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<EmpruntLivre,Long> {
}
