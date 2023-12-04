package com.example.projetangular.services;

import com.example.projetangular.entities.Reclamation;
import com.example.projetangular.entities.Utilisateur;
import com.example.projetangular.repositories.ReclamationRepository;
import com.example.projetangular.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReclamationServiceImp implements IReclamationService{

    private final ReclamationRepository reclamationRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public Reclamation ajouterReclamation(Reclamation reclamation,String email ) {
        Optional<Utilisateur> e = utilisateurRepository.findByEmail(email);
        if(e.isPresent()){
            reclamation.setEtudiant(e.get());
        }
        reclamation.setEtat("envoyé");
        return reclamationRepository.save(reclamation);
    }
    @Override
    public Reclamation modifierReclamation(Reclamation reclamation,long id) {
        Reclamation rec  = reclamationRepository.findById(id).orElse(null);
        rec.setSujet(reclamation.getSujet());
        rec.setDescription(reclamation.getDescription());
            return reclamationRepository.save(rec);
    }

    @Override
    public Reclamation changerEtatReclamation(Long id, String etat) {
        Reclamation reclamation = reclamationRepository.findById(id).orElse(null);
        reclamation.setEtat(etat);
        return reclamationRepository.save(reclamation);
    }

    @Override
    public List<Reclamation> listeReclamationsParEtudiant(String email,String etat) {
        Utilisateur e = utilisateurRepository.findByEmail(email).get();
       return reclamationRepository.findByEtudiantAndEtatIsNot(e,etat);
    }

    @Override
    public List<Reclamation> listeReclamations(String etat) {
        return reclamationRepository.findByEtatIsNot(etat);
    }


}