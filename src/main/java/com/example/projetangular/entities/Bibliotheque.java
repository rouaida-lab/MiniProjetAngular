package com.example.projetangular.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    String nomB;
    String email;
    long numTel;
    String horaire;
    String description;
    String imageB;

    @OneToMany(mappedBy = "bibliotheque")
    @JsonManagedReference
    Set<Evenement> evenements = new HashSet<>();

    @OneToMany()
    Set<Livre> livres = new HashSet<>();


    @OneToOne(mappedBy = "bibliotheque")
    @JsonManagedReference
    private Foyer foyer;

}
