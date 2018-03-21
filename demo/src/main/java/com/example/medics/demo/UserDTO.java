package com.example.medics.demo;
public class UserDTO {
    private String id;
    //    private String username;
    private String password;
    private String email;
    private String name;
    private boolean isVerified;


    public UserDTO() {

        this.isVerified = false;
    }

    public UserDTO(String id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.isVerified = false;

    }
    public UserDTO(String email, String password, String name) {
        this.password = password;
        this.email = email;
        this.isVerified = false;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }

    public boolean getVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}

