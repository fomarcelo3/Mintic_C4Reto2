package com.mintic.tienda.service;

import com.mintic.tienda.model.Clothe;
import com.mintic.tienda.repository.ClotheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClotheService {

    @Autowired
    private ClotheRepository clotheRepository;

    public List<Clothe> getAll(){
        return clotheRepository.getAll();
    }

    public Clothe create(Clothe clothe){
        if (clothe.getReference() == null){
            return clothe;
        }else {
            return clotheRepository.newClothe(clothe);
        }
    }

    public Optional<Clothe> getClothe(String reference){
        return clotheRepository.getClothe(reference);
    }

    public Clothe update(Clothe clothe){
        if (clothe.getReference() != null){
            Optional<Clothe> clotheBD = clotheRepository.getClothe(clothe.getReference());
            if (!clotheBD.isEmpty()){
                if (clothe.getCategory() != null){
                    clotheBD.get().setCategory(clothe.getCategory());
                }
                if (clothe.getSize() != null){
                    clotheBD.get().setSize(clothe.getSize());
                }
                if (clothe.getDescription() != null){
                    clotheBD.get().setDescription(clothe.getDescription());
                }
                if (clothe.getPrice() != 0.0){
                    clotheBD.get().setPrice(clothe.getPrice());
                }
                if (clothe.getQuantity() != 0){
                    clotheBD.get().setQuantity(clothe.getQuantity());
                }
                if (clothe.getPhotography() != null){
                    clotheBD.get().setPhotography(clothe.getPhotography());
                }
                clotheBD.get().setAvailability(clothe.isAvailability());
                clotheRepository.update(clotheBD.get());
                return clotheBD.get();
            }else
                return clothe;
        }else {
            return clothe;
        }
    }

    public boolean delete(String reference){
        Boolean aBoolean = getClothe(reference).map(clothe -> {
            clotheRepository.delete(clothe);
            return  true;
        }).orElse(false);
        return aBoolean;
    }


    public List<Clothe> getFindByPrice(double price){
        return  clotheRepository.getByPrice(price);
    }

    public List<Clothe> getByDescription(String description){
        return  clotheRepository.getBydescription(description);
    }

}
