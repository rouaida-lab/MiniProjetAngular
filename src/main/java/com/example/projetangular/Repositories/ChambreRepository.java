package com.example.projetangular.Repositories;

import com.example.projetangular.entities.Bloc;
import com.example.projetangular.entities.Chambre;
import com.example.projetangular.entities.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ChambreRepository extends JpaRepository<Chambre,Long> {
    Chambre findByNumeroChambre(long num);
    List<Chambre> findChambresByBloc(Bloc b);

    List<Chambre> findChambresByTypeChambreAndBloc(TypeChambre type, Bloc bloc);
   // List<Chambre>findChambresByReservationsIsContaining();
    //List<Chambre> findChambresByBlocAndTypeChambre(Bloc b , TypeChambre type);
 }
