package com.mintic.tienda.repository.crud;

import com.mintic.tienda.model.Clothe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClotheCrudRepository extends MongoRepository<Clothe,String> {
    public List<Clothe> findByPrice(double price);
    public List<Clothe> findByDescriptionContainingIgnoreCase(String description);
}
