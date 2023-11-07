package com.example.projetangular.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Bloc implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    long idBloc;
    String nomBloc ;
    long capaciteBloc ;
    @ManyToOne()
    Foyer foyer;
    @OneToMany(mappedBy="bloc")
    private Set<Chambre> chambres;

}
