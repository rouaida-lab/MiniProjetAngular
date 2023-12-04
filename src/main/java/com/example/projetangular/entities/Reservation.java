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
@ToString
@EqualsAndHashCode
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    private Date anneeUniversitaire;
    private Boolean estValide ;


    //@ManyToMany(mappedBy="reservations")//reservation fils
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    // : lorsque on supprime l'etudiant on supp rime la reservation aussi
    private Set<Utilisateur> etudiants;

    @ManyToOne
    @JsonIgnore
    Chambre chambre;



}

