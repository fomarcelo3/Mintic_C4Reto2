package com.mintic.tienda.controller;

import com.mintic.tienda.model.Clothe;
import com.mintic.tienda.model.User;
import com.mintic.tienda.service.ClotheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
* Controlador clase Clothe
* */
@RestController
@RequestMapping("api/clothe")
@CrossOrigin("*")
public class ClotheController {

    @Autowired
    private ClotheService clotheService;

    @GetMapping("/all")
    public List<Clothe> getAll(){
        return clotheService.getAll();
    }

    @GetMapping("/{reference}")
    public Optional<Clothe> getClothe(@PathVariable String reference){
        return clotheService.getClothe(reference);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Clothe newClothe(@RequestBody Clothe clothe){
        return clotheService.create(clothe);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Clothe update(@RequestBody Clothe clothe){
        return  clotheService.update(clothe);
    }

    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable String reference){
        return clotheService.delete(reference);
    }

}
