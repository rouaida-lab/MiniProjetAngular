package com.example.projetangular.controllers;

import com.example.projetangular.entities.Categorie;
import com.example.projetangular.entities.Livre;
import com.example.projetangular.entities.Utilisateur;
import com.example.projetangular.services.ILivreService;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("livres")
public class livreController {

    ILivreService livreService;

    @GetMapping("/all")
    List<Livre> retrieveLivres(){
        return livreService.getAllLivre();
    }


}
