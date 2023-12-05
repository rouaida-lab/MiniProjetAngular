package com.example.projetangular.Repositories;

import com.example.projetangular.entities.EmpruntLivre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<EmpruntLivre,Long> {
}
