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
    private long idBloc;
    private String nomBloc ;
    private long capaciteBloc ;
    @ManyToOne()
    Foyer foyer;
    @OneToMany(mappedBy="bloc")
    private Set<Chambre> chambres;

}
