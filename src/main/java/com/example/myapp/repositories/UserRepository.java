package com.example.myapp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.myapp.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
