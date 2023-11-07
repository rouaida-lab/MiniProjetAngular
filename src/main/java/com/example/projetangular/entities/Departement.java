package com.example.projetangular.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Departement implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    long idDepartement;
    String nom;
    String description;
    String imageDep;
    String email;
    @ManyToOne()
    Universite universite;

}
