
package com.example.projetangular.controllers;

import com.example.projetangular.Exceptions.EmailAlreadyExistsException;
import com.example.projetangular.entities.RoleUtilisateur;
import com.example.projetangular.entities.Universite;
import com.example.projetangular.entities.Utilisateur;
import com.example.projetangular.services.IUtilisateurService;
import com.example.projetangular.services.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("utilisateurs")
public class UtilisateurController {
    private final IUtilisateurService utilisateurService;
    private final LogoutService logoutService;

    // http://localhost:8080/all-etudiants
    @GetMapping("")
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurService.getUtilisateurs();
    }
    @GetMapping("/getByRoleEtat")
    public List<Utilisateur> getByRoleAndEtatIsNot(@RequestParam("role") RoleUtilisateur role,@RequestParam("etat") String etat) {
        return utilisateurService.getEtudiantsByEtatNon(role,etat);
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
    @GetMapping("/confirm")
    public ResponseEntity<String> confirmAccount(@RequestParam("token") String token) {
        // Utilisez le service pour confirmer le compte
        boolean confirmationSuccess = utilisateurService.confirmUserAccount(token);

        if (confirmationSuccess) {
            return ResponseEntity.ok("{\"message\": \"Account confirmed successfully!\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\": \"token Invalid\"}");
        }
    }
    @PutMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logoutService.logout(request, response, auth);

        }
        return ResponseEntity.ok("Logout successful");
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

    @GetMapping("/getByEmail")
    public Utilisateur getUtilisateur(@RequestParam("email") String email) {
        return utilisateurService.getUtilisateurByEmail(email);
    }
    @GetMapping("/students/all")
    public Map<Universite, List<Utilisateur>> getAllStudentsByUniversity() {
        return utilisateurService.getEtudiantsByUniversite();
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
}
