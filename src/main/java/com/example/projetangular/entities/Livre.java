package com.example.projetangular.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    int nbEmprunts;
    int nbLike;
    boolean disponibilite;

    public void incrementerNbEmprunts() {
        this.nbEmprunts++;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    Categorie categorie;


    @OneToMany(mappedBy = "livre")
    @JsonManagedReference
    Set<EmpruntLivre> emprunts = new HashSet<>();
}
