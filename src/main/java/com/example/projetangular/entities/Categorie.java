package com.example.projetangular.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categorie implements Serializable{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    long idCategorie;
    String nom;
    String description;
    String image;
    @OneToMany(mappedBy = "categorie")
    @JsonManagedReference
    Set<Livre>livres = new HashSet<>();
}
