package com.example.projetangular.Repositories;

import com.example.projetangular.entities.Bibliotheque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliothequeRepository extends JpaRepository<Bibliotheque,Long> {
}
