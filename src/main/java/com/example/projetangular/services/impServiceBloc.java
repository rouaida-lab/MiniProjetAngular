package com.example.projetangular.services;

import com.example.projetangular.entities.Bloc;
import com.example.projetangular.entities.Chambre;
import com.example.projetangular.entities.Foyer;
import com.example.projetangular.entities.TypeChambre;
import com.example.projetangular.repositories.BlocRepository;
import com.example.projetangular.repositories.FoyerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class impServiceBloc implements iBloc {
    BlocRepository blocRespository;
    //  ChambreRepository ChambreRepository ;
    FoyerRepository FoyerRepository;

    public impServiceBloc(BlocRepository blocRespository, FoyerRepository foyerRepository) {
        this.blocRespository = blocRespository;
        FoyerRepository = foyerRepository;
    }

    @Override
    public Bloc addBloc(Bloc bloc) {
        // TODO Auto-generated method stub
        return blocRespository.save(bloc);
    }

    @Override
    public Optional<Bloc> getBloc(long idBloc) {
        // TODO Auto-generated method stub
        return blocRespository.findById(idBloc);
    }

    @Override
    public List<Bloc> getAllBloc() {
        // TODO Auto-generated method stub
        return blocRespository.findAll();
    }

    @Override
    public void deleteBloc(long idBloc) {
        blocRespository.deleteById(idBloc);

    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        // TODO Auto-generated method stub
        return blocRespository.save(bloc);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, String nomBloc) {
       /* Bloc bloc = blocRespository.findByNomBloc(nomBloc);
        for( Long i : numChambre )
        {
            Chambre chambre = ChambreRepository.findByNumChambre(i);
            chambre.setBlocidd(bloc);
            ChambreRepository.save(chambre);

        }*/
        return null;
    }

    @Override
    public Bloc affecterBlocAFoyer(String nomBloc, String nomFoyer) {
        Bloc bloc = blocRespository.findByNomBloc(nomBloc);
        System.out.println(bloc.getNomBloc());
        Foyer foyer = FoyerRepository.findByNomFoyer(nomFoyer);
        System.out.println(foyer.getNomFoyer());
        bloc.setFoyer(foyer);
        foyer.getBlocs().add(bloc);
        FoyerRepository.save(foyer);
        blocRespository.save(bloc);
    return blocRespository.save(bloc);
    }

    @Override
    public long nbChambreParTypeEtBloc(TypeChambre type, long idBloc) {
       /* Bloc bloc = blocRespository.findById(idBloc).orElse(null);
        //Chambre chambre = ChambreRepository.getTypeChambre(type);
        int nb =0 ;
        for(Chambre k : bloc.getChambrelist())
        {
            if(k.getTypeC().equals(type))
            {
                nb++;
            }
        }*/
        return 0;
    }

    @Override
    public List<Bloc> getBlocByIDFoyer(long idFoyer) {
        Foyer f=FoyerRepository.findById(idFoyer).orElse(null);
        List<Bloc> list = blocRespository.findBlocsByFoyer(f);
        return list;
    }

    @Override
    public List<Bloc> getBlocNonAffecter() {
        List<Bloc> list = blocRespository.findAll();
        List<Bloc> list1 = new ArrayList<>();
        for(int i=0 ; i<list.size() ; i++)
        {
            if(list.get(i).getFoyer()==null)
            {
                 list1.add(list.get(i));
            }
        }

        return list1;
    }

    @Override
    public Bloc getBlocByNom(String nomBloc) {
        return blocRespository.findByNomBloc(nomBloc);
    }


}
