package com.example.projetangular.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    String idReservation ;
    Date anneeUniversitaire;
    Boolean estValide ;
    @ManyToMany(mappedBy="reservations")//reservation fils
    // : lorsque on supprime l'etudiant on supprime la reservation aussi
    private Set<Utilisateur> etudiants;
}

