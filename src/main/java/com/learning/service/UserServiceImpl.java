package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.model.Provider;
import com.learning.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.model.User;
import com.learning.repository.RoleRepository;
import com.learning.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Autowired RoleRepository roleRepo;
	
	@Override
	public User save(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public List<User> saveAll(List<User> entities) {
		return (List<User>)userRepository.saveAll(entities);
	}

	@Override
	public Optional<User> findById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		return userRepository.existsById(id);
	}

	@Override
	public List<User> findAll() {
		return (List<User>)userRepository.findAll();
	}

	@Override
	public List<User> findAllById(List<String> ids) {
		return (List<User>)userRepository.findAllById(ids);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public void deleteById(String id) {
		userRepository.deleteById(id);
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
	}

	@Override
	public void deleteAllById(List<String> ids) {
		userRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(List<User> entities) {
		userRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	@Override
	public boolean checkLogin(String username, String password) {
		Optional<User> optionalUser = findById(username);
		if(optionalUser.isPresent() && optionalUser.get().getPassword().equals(password))
		{
			
			return true;
		}
		
		return false;
	}
	@Override
	public boolean authorization(String username, String password) {
//		//true la admin
//		// false la user
//		Optional<User> optionalUser = findById(username);
//		// khong can vi chac chan la ton tai tai khoan roi`
//		if(optionalUser.get().getRole().getRole_id().equals(1)) {
//			return true;
//		}
		return false;
	}
	@Override
	public void registerDefaultUser(User user) {
		System.out.println("Dang gan role");
		Role role = roleRepo.findById(2).get(); //tao tai khoan mac dinh gan role User
//		Role role = roleRepo.findById(2).get();
		user.addRole(role);

		userRepository.save(user);
	}
	@Override
	public void processOAuthPostLogin(String username) {
		User existUser = userRepository.getUserByUsername(username);

		if (existUser == null) {
			User newUser = new User();
			newUser.setUsername(username);
			newUser.setProvider(Provider.GOOGLE);
			Role role = roleRepo.findById(2).get(); //tao tai khoan mac dinh gan role User
			newUser.addRole(role);
			userRepository.save(newUser);
		}

	}
}
