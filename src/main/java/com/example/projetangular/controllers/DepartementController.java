package com.example.projetangular.controllers;


import com.example.projetangular.entities.Departement;
import com.example.projetangular.entities.Universite;
import com.example.projetangular.service.IDepartementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("Departement")
public class DepartementController {

    @Autowired
    IDepartementService ds;



    @PostMapping("addDepartment")
    public Departement addDepartment(@RequestBody Departement d) {
        return ds.addDepartment(d);
    }



    @PostMapping("addDepartments")
    public List<Departement> addDepartment(@RequestBody List<Departement> listDepartment) {
        return ds.addDepartment(listDepartment);
    }



    @PutMapping("updateDepartment/{id}")
    public Departement updateDepartment(@RequestBody Departement d,@PathVariable long id) {
        return ds.updateDepartment(d,id);
    }



    @PutMapping("updateDepartments")
    public List<Departement> updateDepartment(@RequestBody List<Departement> listDepartment) {
        return ds.updateDepartments(listDepartment);
    }


    @DeleteMapping("deleteDepartmentbyId")
    public void deleteDepartment(@RequestParam Long id) {
        ds.deleteDepartment(id);
    }



    @DeleteMapping("deleteDepartment")
    public void deleteDepartment(@RequestBody Departement d) {
        ds.deleteDepartment(d);
    }

    @GetMapping("findAllDepartments")
    public List<Departement> findAllDepartment() {
        return ds.findAllDepartment();
    }



    @GetMapping("findDepartmentById")
    public Departement findDepartmentById(@RequestParam Long id) {
        return ds.findDepartmentById(id);
    }


}
