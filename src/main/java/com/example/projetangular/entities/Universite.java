package com.example.projetangular.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    long idUniversite ;
    String nomUniversite;
    String adresse;
    @JsonIgnore
    @OneToOne()
    private Foyer foyer;
    @OneToMany(mappedBy = "universite")
    Set<Departement> departements = new HashSet<>();
}
