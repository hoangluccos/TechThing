package com.learning.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    private String username;

    private String password;

    private String fullname;

    private String mail;

//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private Set<UserRole> userRoles;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
        super();
    }
    public void addRole(Role role) {
        this.roles.add(role);
    }


    //oauth2 login with google
    @Enumerated(EnumType.STRING)
    private Provider provider;

//    public Provider getProvider() {
//        return provider;
//    }
//
//    public void setProvider(Provider provider) {
//        this.provider = provider;
//    }

}
