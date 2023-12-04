package com.example.projetangular.contollers;


import com.example.projetangular.entities.Chambre;
import com.example.projetangular.services.IChambreService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("api/chambres")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 4600)
@AllArgsConstructor
public class ChambreController {



    @Autowired
    IChambreService chambreService;

    @GetMapping("/getAllChambre")
    public Iterable<Chambre>getAllChambre(){
        return chambreService.getAllChambre();
    }


    @PostMapping("/addChambre")
    public  Chambre addChambre(@RequestBody Chambre ch){
        return chambreService.ajouterChambre(ch);
    }

    @DeleteMapping("/deleteChambre/{idChambre}")
    public void deleteChambre(@PathVariable Long idChambre){
        chambreService.supprimerChambreParIdChambre(idChambre);
    }


    @PutMapping("/updateChambre")
    public Chambre updateChambre (@RequestBody Chambre c){
        return chambreService.mettreAJourChambre(c);
    }
    @GetMapping("/getChambreByIDBloc/{idBloc}")
    public List<Chambre> getChambreByIDBloc(@PathVariable long idBloc)
    {
        return  chambreService.getChambreByIDBloc(idBloc);
    }



}
