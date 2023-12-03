package com.example.projetangular.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @JsonIgnore
    @OneToOne(mappedBy = "confirmationToken")
    private Utilisateur user;

    private LocalDateTime expiryDate;
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }

}
