package com.example.projetangular.repositories;

import com.example.projetangular.entities.Bibliotheque;
import com.example.projetangular.entities.Bloc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliothequeRepository extends JpaRepository<Bibliotheque,Long> {
    Bibliotheque findByNomB(String nomB);

}
