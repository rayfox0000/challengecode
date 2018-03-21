package com.example.hooch.medicsource;

/**
 * Created by hooch on 21/3/2018.
 */

public class User {
    public String email;
    public String name;

    public User(){

    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
