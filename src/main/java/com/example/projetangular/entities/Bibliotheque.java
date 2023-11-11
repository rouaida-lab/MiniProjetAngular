package com.example.projetangular.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bibliotheque implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    long idBibliotheque;
    String nom;
    String email;
    long numTel;
    String horaire;
    String description;
    @OneToMany(mappedBy = "bibliotheque")
    Set<Evenement> evenements = new HashSet<>();
    @OneToMany()
    Set<Livre> livres = new HashSet<>();
    @OneToOne(mappedBy = "bibliotheque")
    private Foyer foyer;

}
