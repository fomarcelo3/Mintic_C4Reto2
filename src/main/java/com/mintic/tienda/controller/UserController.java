package com.mintic.tienda.controller;

import com.mintic.tienda.model.User;
import com.mintic.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
 * Controlador clase User
 * */
@RestController
@RequestMapping("api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    /*Instancia de la clase servicio de User*/
    private UserService userService;

    /*Listar todos los elementos de User*/
    @GetMapping("/all")
    public List<User> getAll(){
        return userService.getAll();
    }

    /*Listar un elemento de User*/
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable int id){
        return userService.getUser(id);
    }

    /*Insertar un elemento a User*/
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@RequestBody User user){
        return userService.create(user);
    }

    /*Actualizar un elemento de User*/
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@RequestBody User user){
        return  userService.update(user);
    }

    /*Borrar un elemento de User*/
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable int id){
        return userService.delete(id);
    }

    /*Login de User*/
    @GetMapping("/{email}/{password}")
    public User loginUser(@PathVariable String email, @PathVariable String password){
        return userService.loginUser(email, password);
    }

    /*Validar email de un usuario */
    @GetMapping("/emailexist/{email}")
    public boolean emailExists(@PathVariable String email){
        return userService.emailExists(email);
    }

}
