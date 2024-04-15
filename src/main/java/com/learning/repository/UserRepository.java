package com.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learning.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	User findByUsername(String username);
}
