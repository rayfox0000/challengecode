package com.example.medics.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepo extends MongoRepository<User,String> {

    @Query("{email : ?0,password : ?1}")
    public User findByEmailPassword(String email, String password);

    @Query("{email : ?0,password : ?1,memberType : ?2}")
    public User findByEmailPasswordMemberType(String email, String password, String memberType);

    @Query("{email : ?0}")
    public User findByEmail(String email);
}