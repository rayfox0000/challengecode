package com.example.medics.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {
    @Id
    private String id;
    //private String username;
    private String password;
    private String email;
    private String name;
    private boolean isVerified;

    public User() {
    }

    public User(UserDTO UserDTO) {
        this.id = UserDTO.getId();
        // this.username = UserDTO.getUser();
        this.password = UserDTO.getPassword();
        this.email = UserDTO.getEmail();
        this.name = UserDTO.getName();
        this.isVerified = UserDTO.getVerified();
    }


    public UserDTO toDTO(){
        UserDTO dto = new UserDTO();
        dto.setId(id);
        // dto.setUser(username);
        dto.setPassword(password);
        dto.setEmail(email);
        dto.setName(name);
        dto.setVerified(isVerified);
        return dto;
    }
}

