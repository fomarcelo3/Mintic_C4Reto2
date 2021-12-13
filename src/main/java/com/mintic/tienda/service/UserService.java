package com.mintic.tienda.service;

import com.mintic.tienda.model.User;
import com.mintic.tienda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> getUser(int id){
        return userRepository.getUser(id);
    }

    public User create(User user) {

        //obtiene el maximo id existente en la coleccion
        Optional<User> userIdMaximo = userRepository.lastUserId();

        //si el id del Usaurio que se recibe como parametro es nulo, entonces valida el maximo id existente en base de datos
        if (user.getId() == null) {
            //valida el maximo id generado, si no hay ninguno aun el primer id sera 1
            if (userIdMaximo.isEmpty())
                user.setId(1);
                //si retorna informacion suma 1 al maximo id existente y lo asigna como el codigo del usuario
            else
                user.setId(userIdMaximo.get().getId() + 1);
        }

        Optional<User> e = userRepository.getUser(user.getId());
        if (e.isEmpty()) {
            if (emailExists(user.getEmail())==false){
                return userRepository.newUser(user);
            }else{
                user.setId(null);
                return user;
            }
        }else{
            return user;
        }

    }





    public User update(User user){
        if (user.getId() != null){
            Optional<User> userBD = userRepository.getUser(user.getId());
            if (!userBD.isEmpty()){
                if (user.getIdentification() != null){
                    userBD.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null){
                    userBD.get().setName(user.getName());
                }
                if (user.getAddress() != null){
                    userBD.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null){
                    userBD.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null){
                    userBD.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null){
                    userBD.get().setPassword(user.getPassword());
                }
                if(user.getType() != null){
                    userBD.get().setType(user.getType());
                }
                if (user.getZone() != null){
                    userBD.get().setZone(user.getZone());
                }

                userRepository.update(userBD.get());

                return userBD.get();


            }else {
                return user;
            }
        }else {
            return user;
        }
    }

    public boolean delete(int id){
        Boolean aBoolean = getUser(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public boolean emailExists(String email){
        return userRepository.emailExists(email);
    }

    public User loginUser(String email, String password){
        Optional<User> user = userRepository.authenticateUser(email,password);

        if (user.isEmpty()){
            return new User();
        }else {
            return user.get();
        }
    }
}
