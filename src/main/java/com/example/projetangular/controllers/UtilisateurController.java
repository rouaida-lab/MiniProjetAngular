
package com.example.projetangular.controllers;

import com.example.projetangular.Exceptions.EmailAlreadyExistsException;
import com.example.projetangular.entities.Universite;
import com.example.projetangular.entities.Utilisateur;
import com.example.projetangular.services.IUtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RequestMapping("utilisateurs")
public class UtilisateurController {
    private final IUtilisateurService utilisateurService;

    // http://localhost:8080/all-etudiants
    @GetMapping("")
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurService.getUtilisateurs();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Utilisateur utilisateur) {
        try {
            Utilisateur savedUtilisateur = utilisateurService.addUtilisateur(utilisateur);
            return new ResponseEntity<>(savedUtilisateur, HttpStatus.CREATED);
        } catch (EmailAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Utilisateur utilisateur = utilisateurService.login(loginRequest.getEmail(), loginRequest.getPassword());
            // You can perform additional actions here if login is successful
            return new ResponseEntity<>(utilisateur, HttpStatus.OK);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            // Handle authentication failure
            return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
        }
    }


    private static class LoginRequest {
        private String email;
        private String password;

        // Getter for email
        public String getEmail() {
            return email;
        }

        // Setter for email
        public void setEmail(String email) {
            this.email = email;
        }

        // Getter for password
        public String getPassword() {
            return password;
        }

        // Setter for password
        public void setPassword(String password) {
            this.password = password;
        }
    }

    @PutMapping("/edit/{idUtilisateur}")
    public Utilisateur editUtilisateur(@PathVariable("idUtilisateur") Long id ,@RequestBody Utilisateur c) {
        return
                utilisateurService.updateUtilisateur(id,c);
    }
    @PutMapping("/etat/{idUtilisateur}")
    public Utilisateur etatUtilisateur(@PathVariable("idUtilisateur") Long id ,@RequestBody String etat) {
        return
                utilisateurService.changerEtatUtilisateur(id,etat);
    }
    @DeleteMapping("/delete/{idUtilisateur}")
    public void deleteUtilisateur(@PathVariable("idUtilisateur") Long id) {
        utilisateurService.deleteUtilisateur(id);
    }

    @GetMapping("/get/{idUtilisateur}")
    public Utilisateur getUtilisateur(@PathVariable("idUtilisateur") Long id) {
        return utilisateurService.getUtilisateur(id);
    }
    @GetMapping("/students/all")
    public Map<Universite, List<Utilisateur>> getAllStudentsByUniversity() {
        return utilisateurService.getEtudiantsByUniversite();
    }
}
