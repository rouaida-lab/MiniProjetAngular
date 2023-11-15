package com.example.projetangular.services;

import com.example.projetangular.entities.Livre;
import com.example.projetangular.repositories.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreServiceImpl implements ILivreService{

    LivreRepository livreRepository;

    public LivreServiceImpl(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    @Override
    public Livre addLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    @Override
    public Livre getLivre(long idLivre) {
        return livreRepository.findById(idLivre).orElse(null);
    }

    @Override
    public List<Livre> getAllLivre() {
        return livreRepository.findAll();
    }

    @Override
    public void deleteLivre(long idLivre) {
        livreRepository.deleteById(idLivre);
    }

    @Override
    public Livre updateLivre(Livre livre) {
        return livreRepository.save(livre);
    }
}
