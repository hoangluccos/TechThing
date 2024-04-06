package com.learning.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.model.Role;
import com.learning.repository.RoleRepository;
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Optional<Role> findById(Integer id) {
		return roleRepository.findById(id);
	}

}
