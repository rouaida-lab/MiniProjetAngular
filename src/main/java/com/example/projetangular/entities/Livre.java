package com.example.projetangular.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Livre implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    long idLivre;
    String titre;
    String nomAuteur;
    int nbPages;
    Date dateDePublication;
    String Description;
    String image;
    String etat;
    Date dateDebutEmprunt;
    Date dateFinEmprunt;
    @ManyToOne()
    Categorie categorie;
    @ManyToMany()
    Set<Utilisateur> enprunteParEtudiants = new HashSet<>();
}
