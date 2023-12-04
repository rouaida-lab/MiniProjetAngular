package com.example.projetangular.repositories;

import com.example.projetangular.entities.Bloc;
import com.example.projetangular.entities.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface BlocRepository extends JpaRepository<Bloc,Long> {
    Bloc findByNomBloc(String nomBloc);


    List<Bloc> findBlocsByFoyer(Foyer foyer);






}
