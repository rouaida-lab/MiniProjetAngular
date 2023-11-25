package com.example.projetangular.repositories;

import com.example.projetangular.entities.Bloc;
import com.example.projetangular.entities.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;

public interface BlocRepository extends JpaRepository<Bloc,Long> {
    Bloc findByNomBloc(String nom);

    List<Bloc> findBlocsByFoyer(Foyer foyer);



    public List<Bloc> findBlocByCapaciteBlocAndNomBloc(Long capacite,String name);
    void deleteByCapaciteBloc(Long capacite);
    Bloc findBlocByIdBloc(Long idBloc);
    @Query("SELECT b FROM Bloc b JOIN b.chambres c JOIN c.reservations r WHERE r.anneeUniversitaire = :anneeUniversitaire")
    Bloc getBlocByChambreReservationAnneeUniversitaire(@Param("anneeUniversitaire") Date anneeUniversitaire);

}
