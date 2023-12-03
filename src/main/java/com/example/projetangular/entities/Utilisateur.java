package com.example.projetangular.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur implements UserDetails, Serializable {
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
    String etat;
    String token;
    @OneToOne()
    ConfirmationToken confirmationToken;

    @ManyToMany()
    private Set<Reservation> reservations = new HashSet<>();
    @OneToMany(mappedBy = "etudiantEmp")
    Set<EmpruntLivre>emprunts = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "etudiant")
    Set<Reclamation>reclamations =new HashSet<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));


    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}