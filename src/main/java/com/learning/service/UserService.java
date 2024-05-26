package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learning.model.User;
import com.learning.repository.UserRepository;


public interface UserService {

	void deleteAll();

	void deleteAll(List<User> entities);

	void deleteAllById(List<String> ids);

	void delete(User entity);

	void deleteById(String id);

	long count();

	List<User> findAllById(List<String> ids);
	User findByUsername(String username);

	List<User> findAll();

	boolean existsById(String id);

	Optional<User> findById(String id);

	List<User> saveAll(List<User> entities);

	User save(User entity);
	
	boolean checkLogin (String username, String password);

	boolean authorization(String username, String password);

	void registerDefaultUser(User user);
	void processOAuthPostLogin(String username);

}
