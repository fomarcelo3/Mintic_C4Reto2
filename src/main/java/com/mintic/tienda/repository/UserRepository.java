package com.mintic.tienda.repository;

import com.mintic.tienda.model.User;
import com.mintic.tienda.repository.crud.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    private UserCrudRepository crudInterface;

    public List<User> getAll(){
        return crudInterface.findAll();
    }

    public Optional<User> getUser(Integer id){
        return  crudInterface.findById(id);
    }

    public User newUser(User user){
        return crudInterface.save(user);
    }
    public void update(User user){
        crudInterface.save(user);
    }

    public void delete(User user){
        crudInterface.delete(user);
    }

    public  boolean emailExists(String email){
        Optional<User> usuario = crudInterface.findByEmail(email);
        return !usuario.isEmpty();
    }

    public Optional<User> authenticateUser(String email, String password){
        return crudInterface.findByEmailAndPassword(email,password);
    }

    public Optional<User> lastUserId(){
        return crudInterface.findTopByOrderByIdDesc();
    }


}
