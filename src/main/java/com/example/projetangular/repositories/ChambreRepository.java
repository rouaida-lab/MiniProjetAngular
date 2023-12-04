package com.example.projetangular.repositories;

import com.example.projetangular.entities.Bloc;
import com.example.projetangular.entities.Chambre;
import com.example.projetangular.entities.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChambreRepository extends CrudRepository<Chambre,Long> {
   /* Chambre findByNumeroChambre(long num);
    List<Chambre> findChambresByBloc(Bloc b);

    List<Chambre> findChambresByTypeChambreAndBloc(TypeChambre type, Bloc bloc);
   // List<Chambre>findChambresByReservationsIsContaining();
    //List<Chambre> findChambresByBlocAndTypeChambre(Bloc b , TypeChambre type);

*/
    public List<Chambre> findBlocByNumeroChambre(Long numeroChambre);
    void deleteByNumeroChambre(Long numeroChambre);
    void deleteByIdChambre(Long idChambre);
    Chambre findChambreByIdChambre(Long idChambre);
 List<Chambre> findChambresByBloc(Bloc b);

    @Query("SELECT c FROM Chambre c JOIN c.bloc b JOIN b.foyer f JOIN f.universite u WHERE u.idUniversite=: idUniversite")
    List<Chambre> getChambreByUniversite(@Param("idUniversite")Long idUniversite);

 }
