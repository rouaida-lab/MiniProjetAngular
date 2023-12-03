package com.example.projetangular.controllers;
import com.example.projetangular.entities.Reclamation;
import com.example.projetangular.services.ReclamationServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reclamations")
public class ReclamationController {

    private final ReclamationServiceImp reclamationService;


    @PostMapping("/ajouter")
    public ResponseEntity<Reclamation> ajouterReclamation(@RequestBody Reclamation reclamation,@RequestParam("email") String email) {
        Reclamation nouvelleReclamation = reclamationService.ajouterReclamation(reclamation,email);
        return ResponseEntity.ok(nouvelleReclamation);
    }
    @GetMapping("/mesRec")
    public ResponseEntity<List<Reclamation>> ajouterReclamation(@RequestParam("email") String email,@RequestParam("etat") String etat) {
       List<Reclamation> lisR = reclamationService.listeReclamationsParEtudiant(email,etat);
        if (lisR != null) {
            return ResponseEntity.ok(lisR);  // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 404 Not Found
        }    }

    @GetMapping("/listeRec")
    public ResponseEntity<List<Reclamation>> getReclamation(@RequestParam("etat") String etat) {
        List<Reclamation> lisR = reclamationService.listeReclamations(etat);
        if (lisR != null) {
            return ResponseEntity.ok(lisR);  // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 404 Not Found
        }
    }
    @PutMapping("/modifier/{id}")
    public ResponseEntity<Reclamation> modifierReclamation(@RequestBody Reclamation reclamation,@PathVariable("id") Long id ) {
        return ResponseEntity.ok(reclamationService.modifierReclamation(reclamation,id));
    }
    @PutMapping("/changerEtat")
    public ResponseEntity<Reclamation> changerEtatReclamation(@RequestBody Reclamation reclamation,@RequestParam("etat") String etat) {
        return ResponseEntity.ok(reclamationService.changerEtatReclamation(reclamation,etat));
    }
}
