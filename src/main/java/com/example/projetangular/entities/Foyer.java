package com.example.projetangular.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Foyer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idFoyer;
    String nomFoyer;
    long capaciteFoyer;
    String image ;
    String description ;

    String Type ;
    @JsonManagedReference
    @OneToMany(mappedBy="foyer",cascade = CascadeType.ALL)
    private Set<Bloc> blocs;

    @OneToOne(mappedBy = "foyer")
    private Universite universite;

    @OneToOne()
    @JsonBackReference
    private Bibliotheque bibliotheque;
    public Foyer(String nomFoyer, long capaciteFoyer, String image, String description, String type, Set<Bloc> blocs, Universite universite) {
        this.nomFoyer = nomFoyer;
        this.capaciteFoyer = capaciteFoyer;
        this.image = image;
        this.description = description;
        Type = type;
        this.blocs = blocs;
        this.universite = universite;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Foyer() {
        super();
    }

    public long getIdFoyer() {
        return idFoyer;
    }



    public String getNomFoyer() {
        return nomFoyer;
    }

    public void setNomFoyer(String nomFoyer) {
        this.nomFoyer = nomFoyer;
    }

    public long getCapaciteFoyer() {
        return capaciteFoyer;
    }

    public void setCapaciteFoyer(long capaciteFoyer) {
        this.capaciteFoyer = capaciteFoyer;
    }

    public Set<Bloc> getBlocs() {
        return blocs;
    }

    public void setBlocs(Set<Bloc> blocs) {
        this.blocs = blocs;
    }

    public Universite getUniversite() {
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }
}