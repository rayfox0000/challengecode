package com.example.medics.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserService {
    @Autowired
    private UserRepo UserRepo;

    @Transactional
    public UserDTO findByEmailPassword(String email, String password){
        System.out.println("username"+email + " "+password);
        User user = UserRepo.findByEmailPassword(email,password);
        if(user != null){
            return user.toDTO();
        }
        else{
            return null;
        }
    }

    @Transactional
    public UserDTO findByEmailPasswordMemberType(String email, String password, String memberType){
        System.out.println("username"+email + " "+password);
        User user = UserRepo.findByEmailPasswordMemberType(email,password,memberType);
        if(user != null){
            return user.toDTO();
        }
        else{
            return null;
        }
    }
    public void save(UserDTO UserDTO){
        User user = new User(UserDTO);
        UserRepo.save(user);
    }
    @Transactional
    public UserDTO findByEmail(String email){
        User user = UserRepo.findByEmail(email);
        if(user != null){
            return user.toDTO();
        }
        else{
            return null;
        }
    }
}


