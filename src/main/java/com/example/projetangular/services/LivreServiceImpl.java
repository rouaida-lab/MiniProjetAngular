package com.example.projetangular.services;


import com.example.projetangular.entities.Livre;
import com.example.projetangular.repositories.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreServiceImpl implements ILivreService {

    public LivreServiceImpl(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    LivreRepository livreRepository;
    @Override
    public List<Livre> getAllLivre() {
        return livreRepository.findAll() ;
    }

    @Override
    public Livre getLivre(long idLivre) {
        return livreRepository.findById(idLivre).orElse(null);
    }
}
