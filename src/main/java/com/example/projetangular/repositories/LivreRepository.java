package com.example.projetangular.repositories;

import com.example.projetangular.entities.Categorie;
import com.example.projetangular.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LivreRepository extends JpaRepository<Livre,Long> {


    List<Livre> findAllByCategorie(Categorie categorie);
}
