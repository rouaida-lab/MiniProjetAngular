package com.example.projetangular.services;

import com.example.projetangular.entities.Bloc;
import com.example.projetangular.entities.TypeChambre;

import java.util.List;
import java.util.Optional;

public interface iBloc {
    Bloc addBloc(Bloc bloc) ;
    Optional<Bloc> getBloc(long idBloc );
    List<Bloc> getAllBloc();
    void deleteBloc(long idBloc);
    Bloc updateBloc(Bloc bloc);
    Bloc affecterChambresABloc(List<Long> numChambre, String nomBloc) ;
    Bloc affecterBlocAFoyer( String nomBloc, String nomFoyer) ;
    long nbChambreParTypeEtBloc(TypeChambre type, long idBloc) ;

    List<Bloc> getBlocByIDFoyer(long idFoyer);
    List<Bloc> getBlocNonAffecter();
    Bloc getBlocByNom(String nomBloc);

}
