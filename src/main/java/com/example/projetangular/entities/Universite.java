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
public class Universite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUniversite")
    long idUniversite ;

    @Column(name="nomUniversite")
    String nomUniversite;

    @Column(name="adresse")
    String adresse;

    @OneToOne()
    private Foyer foyer;

    @OneToMany(mappedBy = "universite")
    Set<Departement> departements = new HashSet<>();
}
