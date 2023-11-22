package com.example.projetangular.services;

import com.example.projetangular.entities.EmpruntLivre;
import com.example.projetangular.repositories.EmpruntLivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmpruntLivreServiceImpl implements IEmpruntLivreService {


    EmpruntLivreRepository empruntLivreRepository ;

    public EmpruntLivreServiceImpl(EmpruntLivreRepository empruntLivreRepository) {
        this.empruntLivreRepository = empruntLivreRepository;
    }

    @Override
    public EmpruntLivre addEmpruntLivre(EmpruntLivre empruntLivre) {
        return empruntLivreRepository.save(empruntLivre);
    }

    @Override
    public EmpruntLivre getEmpruntLivre(long idEmpruntLivre) {
        return empruntLivreRepository.findById(idEmpruntLivre).orElse(null);
    }

    @Override
    public List<EmpruntLivre> getAllEmpruntLivres() {
        return empruntLivreRepository.findAll();
    }

    @Override
    public void deleteEmpruntLivre(long idEmpruntLivre) {
        empruntLivreRepository.deleteById(idEmpruntLivre);
    }

    @Override
    public EmpruntLivre updateEmpruntLivre(EmpruntLivre empruntLivre) {
        return empruntLivreRepository.save(empruntLivre);
    }

    @Override
    public EmpruntLivre accepterEmpruntLivre(long idEmpruntLivre) {
        EmpruntLivre empruntlivre = empruntLivreRepository.findById(idEmpruntLivre).orElse(null);
        empruntlivre.setEtat("valide");
        return empruntLivreRepository.save(empruntlivre);
    }

    @Override
    public void refuserEmpruntLivre(long idEmpruntLivre) {
        EmpruntLivre empruntLivre = empruntLivreRepository.findById(idEmpruntLivre).orElse(null);
        empruntLivreRepository.deleteById(idEmpruntLivre);
    }
}
