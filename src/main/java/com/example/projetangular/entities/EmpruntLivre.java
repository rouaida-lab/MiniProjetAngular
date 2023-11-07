package com.example.projetangular.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpruntLivre implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    long idEmprunt;
    Date dateDebutEmprunt;
    Date dateFinEmprunt;
    String etat;
    @ManyToOne()
    Livre livre ;
    @ManyToOne()
    Utilisateur etudiantEmp;
}
