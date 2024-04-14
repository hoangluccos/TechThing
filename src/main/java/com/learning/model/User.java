package com.learning.model;

import java.io.Serializable;
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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;

    public User() {
        super();
    }

}
