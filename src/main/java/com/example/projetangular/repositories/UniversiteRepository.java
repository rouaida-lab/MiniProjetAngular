package com.example.projetangular.repositories;

import com.example.projetangular.entities.Universite;
import com.example.projetangular.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UniversiteRepository extends JpaRepository<Universite,Long> {
    @Query("select u From Universite u where u.nomUniversite=:nomU")//c'est une requette JPQL
    //la difference avec Sql est que sql:table , JpQl:des objets: des rq sql oriente objet
   // @Query(value="select * form Universite u whereu.nom_universite=:nomU",nativeQuery = true)
    Universite retreiveByNom(@Param("nomU") String nomUni);
    Universite findByNomUniversite(String nom); //keyword

}
