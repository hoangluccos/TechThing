package com.learning.service;

import java.util.Optional;

import com.learning.model.Role;

public interface RoleService {

	Optional<Role> findById(Integer id);

}
