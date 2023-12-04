package com.example.projetangular.services;

import com.example.projetangular.entities.Bloc;
import com.example.projetangular.entities.Foyer;
import com.example.projetangular.repositories.FoyerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class impServiceFoyer implements iFoyer {
    FoyerRepository FoyerRespository  ;


    public impServiceFoyer(FoyerRepository foyerRespository) {
        super();
        FoyerRespository = foyerRespository;
    }


    @Override
    public Foyer addFoyer(Foyer foyer) {
        // TODO Auto-generated method stub
        return FoyerRespository.save(foyer);
    }


    @Override
    public Optional<Foyer> getFoyer(long idFoyer) {
        // TODO Auto-generated method stub
        return FoyerRespository.findById(idFoyer);
    }


    @Override
    public List<Foyer> getAllFoyer() {
        // TODO Auto-generated method stub
        return FoyerRespository.findAll();
    }


    @Override
    public void deleteFoyer(long idFoyer) {
        FoyerRespository.deleteById(idFoyer);

    }


    @Override
    public Foyer updateFoyer(Foyer foyer) {
        // TODO Auto-generated method stub
        return FoyerRespository.save(foyer);
    }

    @Override
    public Optional<Foyer> getFoyerById(Long id) {
        return FoyerRespository.findById(id);
    }

    @Override
    public long nombreBlocParFoyer(long idFoyer) {

        Foyer  foyer = FoyerRespository.findById(idFoyer).orElse(null);


        return foyer.getBlocs().size();
    }

    @Override
    public List<Foyer> getFoyerParTypeMixte() {
        List<Foyer> f = FoyerRespository.findAll();
        List<Foyer> mixte = new ArrayList<>();
        for(Foyer l : f)
        {
            if(l.getType().equals("mixte"))
            {
                mixte.add(l);
            }
        }
        return mixte;
    }

    @Override
    public List<Foyer> getFoyerParTypeSexe() {
        List<Foyer> f1 = FoyerRespository.findAll();
        List<Foyer> sexeType = new ArrayList<>();
        for(Foyer l : f1)
        {
            if(l.getType().equals("Fille") || l.getType().equals("garcon")  )
            {
                sexeType.add(l);
            }
        }
        return sexeType;
    }


}
