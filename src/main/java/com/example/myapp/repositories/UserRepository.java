package com.example.myapp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.myapp.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("SELECT user FROM User user WHERE user.username=:username AND user.password=:password")
	public User findUserByCredentials(@Param("username") String u, @Param("password") String p);
	
	@Query("SELECT user FROM User user WHERE user.username=:username")
	public User findUserBYUsername(@Param("username") String username);

}
