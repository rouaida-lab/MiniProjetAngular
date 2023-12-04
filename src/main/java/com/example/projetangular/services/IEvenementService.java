package com.example.projetangular.services;

import com.example.projetangular.entities.Bibliotheque;
import com.example.projetangular.entities.Evenement;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface IEvenementService {
    Evenement addEvenement(Evenement evenement );

    Evenement getEvenement(long idevenement);

    List<Evenement> getAllEvenement();

    void deleteEvenement(long idevenement);

    Evenement updateEvenement(long idEvenement ,Evenement evenement);
    public Evenement affecterEvenementABibliotheque( String nomE, String nomB) ;
    public void updateEventStartDate(long eventId, Date newStartDate);
    public List<Evenement> getAllEvenementsByBibliotheque(Bibliotheque bibliotheque);

    }