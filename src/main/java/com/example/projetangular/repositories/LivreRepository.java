package com.example.projetangular.repositories;

import com.example.projetangular.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre,Long> {
}
