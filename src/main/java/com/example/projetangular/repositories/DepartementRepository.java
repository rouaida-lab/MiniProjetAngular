package com.example.projetangular.repositories;

import com.example.projetangular.entities.Departement;
import com.example.projetangular.entities.Universite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartementRepository extends JpaRepository<Departement,Long> {
    List<Departement> findByUniversite(Universite universite);
}
