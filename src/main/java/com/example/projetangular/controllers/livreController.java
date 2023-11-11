package com.example.projetangular.controllers;
import com.example.projetangular.entities.Livre;
import com.example.projetangular.services.ILivreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("livres")
public class livreController {

    ILivreService livreService;


    @PostMapping("/add")
    Livre addLivre(@RequestBody Livre livre){
        return livreService.addLivre(livre);
    }


    @GetMapping("/{id}")
    Livre retrieveLivre(@PathVariable Long id){
        return livreService.getLivre(id);
    }


    @GetMapping("")
    List<Livre> retrieveLivres(){
        return livreService.getAllLivre();
    }


    @DeleteMapping("/{id}")
    void deleteLivre(@PathVariable Long id){
        livreService.deleteLivre(id);
    }


    @PutMapping("")
    Livre updateLivre(@RequestBody Livre livre){
        return livreService.updateLivre(livre);
    }

}
