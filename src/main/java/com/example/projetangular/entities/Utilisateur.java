package com.example.projetangular.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idEtudiant;
    @Enumerated(EnumType.STRING)
    RoleUtilisateur role;
    String nomEt;
    String prenomEt;
    String email;
    String password;
    long cin;
    String ecole;
    Date dateNaissance;
    @ManyToMany()
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "etudiantEmp")
    @JsonManagedReference
    Set<EmpruntLivre>emprunts = new HashSet<>();
}