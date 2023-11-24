package com.example.projetangular.services;


import com.example.projetangular.entities.Utilisateur;
import com.example.projetangular.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements IUtilisateurService {


    UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }


    @Override
    public Utilisateur getUtilisateurByEmprunt(long idEmprunt) {
        return utilisateurRepository.findByEmpruntId(idEmprunt);
    }

    @Override
    public String getEmailUtilisateur(long id) {
        return utilisateurRepository.findEmailByUserId(id);
    }


}
