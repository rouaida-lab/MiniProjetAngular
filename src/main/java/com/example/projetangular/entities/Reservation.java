package com.example.projetangular.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    private Date anneeUniversitaire;
    Boolean estValide ;

    //@ManyToMany(mappedBy="reservations")//reservation fils
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    // : lorsque on supprime l'etudiant on supprime la reservation aussi
    private Set<Utilisateur> etudiants;

    @ManyToOne
    @JsonIgnore
    Chambre chambre;



}

