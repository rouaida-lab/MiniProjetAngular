package com.example.projetangular.Repositories;

import com.example.projetangular.entities.Bloc;
import com.example.projetangular.entities.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface BlocRepository extends JpaRepository<Bloc,Long> {
    Bloc findByNomBloc(String nom);

    List<Bloc> findBlocsByFoyer(Foyer foyer);


}
