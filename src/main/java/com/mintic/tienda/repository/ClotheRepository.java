package com.mintic.tienda.repository;

import com.mintic.tienda.model.Clothe;
import com.mintic.tienda.repository.crud.ClotheCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClotheRepository {

    @Autowired
    private ClotheCrudRepository clotheCrudRepository;

    public List<Clothe> getAll(){
        return clotheCrudRepository.findAll();
    }

    public Optional<Clothe> getClothe(String reference){
        return  clotheCrudRepository.findById(reference);
    }

    public Clothe newClothe(Clothe clothe){
        return clotheCrudRepository.save(clothe);
    }

    public Clothe update(Clothe clothe){
        return clotheCrudRepository.save(clothe);
    }

    public void delete(Clothe clothe){
        clotheCrudRepository.delete(clothe);
    }
}
