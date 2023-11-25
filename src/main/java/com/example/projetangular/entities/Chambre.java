package com.example.projetangular.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chambre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private long idChambre;
    private long numeroChambre;
    @Enumerated(EnumType.STRING)
    //IL FAUT AJOUTER CETTE LIGNE PAR DEFAUT IL LA DECLARE 0  1 2
    private TypeChambre typeChambre;


    @ManyToOne()
      Bloc bloc;

    @OneToMany(mappedBy = "chambre",cascade = CascadeType.ALL)
    private Set<Reservation> reservations;





}