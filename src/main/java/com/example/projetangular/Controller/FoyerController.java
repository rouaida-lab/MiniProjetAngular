package com.example.projetangular.Controller;

import com.example.projetangular.Service.impServiceBloc;
import com.example.projetangular.Service.impServiceFoyer;
import com.example.projetangular.entities.Foyer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FoyerController {

     impServiceFoyer FoyerserviceImpl;

    public FoyerController(impServiceFoyer impServiceFoyer) {
        this.FoyerserviceImpl = impServiceFoyer;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addFoyer")
    Foyer addFoyer(@RequestBody Foyer foyer)
    {
        return FoyerserviceImpl.addFoyer(foyer);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/Foyer")
    List<Foyer> GetFoyer()
    {
        return FoyerserviceImpl.getAllFoyer();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/Foyer/{id}")
    Optional<Foyer> GetFoyerById(@PathVariable Long id)
    {
        return FoyerserviceImpl.getFoyer(id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/Foyer/update")
    Foyer updateFoyer(@RequestBody Foyer foyer)
    {
        return FoyerserviceImpl.updateFoyer(foyer);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/Foyer/{id}")
    void deleteFoyer(@PathVariable Long id )
    {
        FoyerserviceImpl.deleteFoyer(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/Foyer/idFoyer/{id}")
    void getFoyerById(@PathVariable Long id )
    {
        FoyerserviceImpl.getFoyerById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/Foyer/getSumBloc/{idFoyer}")
    public long nombreBlocParFoyer(@PathVariable long idFoyer)
    {
        return FoyerserviceImpl.nombreBlocParFoyer(idFoyer);
    }
/**
    @GetMapping("/Foyer/getFoyerParType/{typeFoyer}")
    List<Foyer> getFoyerParType(@PathVariable String typeFoyer){
        return  FoyerserviceImpl.getFoyerParType(typeFoyer);

    }*/
@GetMapping("/Foyer/getFoyerParTypeSexe")
public List<Foyer> getFoyerParTypeSexe()
{
    return  FoyerserviceImpl.getFoyerParTypeSexe();

}
    @GetMapping("/Foyer/getFoyerParTypeMixte")
    public List<Foyer> getFoyerParTypeMixte()
    {
        return  FoyerserviceImpl.getFoyerParTypeMixte();

    }

}
