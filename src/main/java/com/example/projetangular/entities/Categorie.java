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
public class Categorie implements Serializable{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    long idCategorie;
    String nom;
    String description;
    @OneToMany(mappedBy = "categorie")
    Set<Livre>livres = new HashSet<>();
}
