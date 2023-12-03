package com.example.projetangular.repositories;

import com.example.projetangular.entities.Bloc;
import com.example.projetangular.entities.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoyerRepository extends JpaRepository<Foyer,Long> {
    Foyer findByNomFoyer(String nomFoyer);
   // List<Foyer> findFoyerByType(String typeFoyer);


}
