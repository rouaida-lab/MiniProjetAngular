package com.example.projetangular.Controller;

import com.example.projetangular.Service.impServiceBloc;
import com.example.projetangular.entities.Bloc;
import com.example.projetangular.entities.TypeChambre;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BlockController {
    //reequest body pour eviter le valeur null
    impServiceBloc BlockserviceImpl ;

    public BlockController(impServiceBloc impServiceBloc) {
        this.BlockserviceImpl = impServiceBloc;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addbloc")
     Bloc addBloc(@RequestBody Bloc bloc)
    {
        return BlockserviceImpl.addBloc(bloc);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/bloc")
    List<Bloc> GetBloc()
    {
        return BlockserviceImpl.getAllBloc();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/bloc/{id}")
    Optional<Bloc> GetBlocById(@PathVariable Long id)
    {
        return BlockserviceImpl.getBloc(id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/bloc/update")
    Bloc updateBloc(@RequestBody Bloc bloc)
    {
        return BlockserviceImpl.updateBloc(bloc);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/bloc/{id}")
    void deleteBloc(@PathVariable Long id )
    {
        BlockserviceImpl.deleteBloc(id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/bloc/affecterChambresABloc/{numChambre}/{nomBloc}")
    Bloc affecterChambresABloc(@PathVariable List<Long> numChambre,@PathVariable String nomBloc)
    {
        return  BlockserviceImpl.affecterBlocAFoyer(nomBloc, nomBloc);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/bloc/affecterBlocAFoyer/{nomBloc}/{nomFoyer}")
    public Bloc affecterBlocAFoyer(@PathVariable String nomBloc,@PathVariable String nomFoyer)
    {
        return BlockserviceImpl.affecterBlocAFoyer(nomBloc, nomFoyer);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/bloc/{type}/{idBloc}")
    long nbChambreParTypeEtBloc(@PathVariable TypeChambre type, @PathVariable long idBloc)
    {
        return BlockserviceImpl.nbChambreParTypeEtBloc(type, idBloc);
    }

    @GetMapping("/blocByIdFoyer/{idFoyer}")
    List<Bloc> getBlocs( @PathVariable long idFoyer)
    {
        return BlockserviceImpl.getBlocByIDFoyer(idFoyer);
    }
    @GetMapping("/getBlocsNonAffecter")
    List<Bloc> getBlocsNonAffecter()
    {
        return BlockserviceImpl.getBlocNonAffecter();
    }
    @GetMapping("/getBlocByNom/{nomBloc}")
    Bloc getBlocByNom(@PathVariable String nomBloc) {
        return BlockserviceImpl.getBlocByNom(nomBloc);
    }
}
