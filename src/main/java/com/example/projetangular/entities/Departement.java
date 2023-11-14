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
    @Column(name="idDepartement")
    long idDepartement;

    @Column(name="nomDepartement")
    String nomDepartement;

    @Column(name="responsable")
    String responsable;

    @Column(name="nombreProfesseurs")
    int nombreProfesseurs;

    @Column(name="specialite")
    String specialite;

    @ManyToOne()
    Universite universite;

}
