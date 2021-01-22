package com.mightyjava.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mightyjava.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("FROM User WHERE username = :username")
	public User findByUsername(@Param("username") String username);
	//OR
	@Query("select u FROM User u WHERE u.username = :username")
	public User getUserByUsername(@Param("username") String username);
	//OR
	@Query("select u FROM User u WHERE u.username = :username")
	public User loadUserByUsername(@Param("username") String username);
	
}