package com.example.projetangular.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    Categorie categorie;

    @OneToMany(mappedBy = "livre")
    Set<EmpruntLivre> emprunts = new HashSet<>();
}
