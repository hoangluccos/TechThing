package com.learning.service;

import com.learning.model.CustomUserDetails;
import com.learning.model.Role;
import com.learning.model.User;
//import com.learning.model.UserRole;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

//        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        Set<UserRole> roles = user.getUserRoles();
//
//        for (UserRole userRole : roles) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().getRole_name()));
//        }
//
//        return new CustomUserDetails(user, grantedAuthorities);
        return new CustomUserDetails(user) ;
    }
}
