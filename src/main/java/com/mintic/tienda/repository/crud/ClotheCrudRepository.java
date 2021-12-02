package com.mintic.tienda.repository.crud;

import com.mintic.tienda.model.Clothe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClotheCrudRepository extends MongoRepository<Clothe,String> {
}
