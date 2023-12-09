package com.example.projetangular.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Bloc implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    long idBloc;
    String nomBloc ;
    long capaciteBloc ;
    @JsonBackReference
    @ManyToOne
    Foyer foyer;
    @OneToMany(mappedBy="bloc")
    private Set<Chambre> chambres;

    public Bloc() {
        super();
    }

    public long getIdBloc() {
        return idBloc;
    }

    public Bloc(String nomBloc, long capaciteBloc, Foyer foyer, Set<Chambre> chambres) {
        this.nomBloc = nomBloc;
        this.capaciteBloc = capaciteBloc;
        this.foyer = foyer;
        this.chambres = chambres;
    }

    public String getNomBloc() {
        return nomBloc;
    }

    public void setNomBloc(String nomBloc) {
        this.nomBloc = nomBloc;
    }

    public long getCapaciteBloc() {
        return capaciteBloc;
    }

    public void setCapaciteBloc(long capaciteBloc) {
        this.capaciteBloc = capaciteBloc;
    }

    public Foyer getFoyer() {
        return foyer;
    }

    public void setFoyer(Foyer foyer) {
        this.foyer = foyer;
    }

    public Set<Chambre> getChambres() {
        return chambres;
    }

    public void setChambres(Set<Chambre> chambres) {
        this.chambres = chambres;
    }
}
