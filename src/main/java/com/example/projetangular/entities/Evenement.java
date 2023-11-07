package com.example.projetangular.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evenement implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    long idEvenement;
    String nom;
    Date dateDebut;
    Date dateFin;
    String lieu;
    String description;
    String etat;
    String image;
    @ManyToOne()
    Bibliotheque bibliotheque;

}
