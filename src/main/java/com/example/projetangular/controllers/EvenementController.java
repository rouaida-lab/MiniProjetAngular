package com.example.projetangular.controllers;

import com.example.projetangular.FileUpload;
import com.example.projetangular.UpdateStartDateDTO;
import com.example.projetangular.entities.EtatEvent;
import com.example.projetangular.entities.Evenement;
import com.example.projetangular.services.IEvenementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@CrossOrigin(origins = "*", allowedHeaders = "*")

@AllArgsConstructor
@RestController
@RequestMapping("evenements")

public class EvenementController {
    IEvenementService evenementService;

@PostMapping("/addEvenement")
public Evenement addEvenement(
        @RequestParam("nomE") String nomE,
        @RequestParam("dateDebut") String dateDebut,
        @RequestParam("dateFin") String dateFin,
        @RequestParam("lieu") String lieu,
        @RequestParam("description") String description,
        @RequestParam("etatEvent") EtatEvent etatEvent,
        @RequestParam("image") MultipartFile multipartFile) throws IOException, ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date parseddateDebut = dateFormat.parse(dateDebut);
    Date parseddateFin = dateFormat.parse(dateFin);

    Evenement evenement = new Evenement();
    evenement.setNomE(nomE);
    evenement.setDateDebut(parseddateDebut);
    evenement.setDateFin(parseddateFin);
    evenement.setLieu(lieu);
    evenement.setDescription(description);
    evenement.setEtatEvent(etatEvent);
    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    evenement.setImage(fileName);
    Evenement SavedEvent = evenementService.addEvenement(evenement);
    String uploadDir = "images_events/" + SavedEvent.getIdEvenement();
    FileUpload.saveFile(uploadDir, fileName, multipartFile);
    return SavedEvent;
}

    @GetMapping("/{id}")
    Evenement retrieveEvenement(@PathVariable Long id){

        return evenementService.getEvenement(id);
    }
    @GetMapping("")
    List<Evenement> retrieveEvenements(){

        return evenementService.getAllEvenement();
    }
    @DeleteMapping("/{id}")
    void deleteEvenement(@PathVariable Long id){

        evenementService.deleteEvenement(id);
    }
    @PutMapping("/updateevent/{id}")
    Evenement updateEvenement(@PathVariable Long id ,@RequestBody Evenement evenement){

        return evenementService.updateEvenement(id,evenement);
    }
    @PutMapping("/updateeventStartDate/{idEvenement}")
    public ResponseEntity<Evenement> updateEventStartDate(@PathVariable Long idEvenement, @RequestBody UpdateStartDateDTO updateStartDateDTO) {
        evenementService.updateEventStartDate(idEvenement, updateStartDateDTO.getNewStartDate());
        return ResponseEntity.ok().build();
    }


    @PutMapping("/aff/{nomEvenement}/{nomBibliotheque}")
    public Evenement affecterEvenementABiblio(@PathVariable String nomEvenement,@PathVariable String nomBibliotheque){

        return  evenementService.affecterEvenementABibliotheque(nomEvenement,nomBibliotheque);
    }


}