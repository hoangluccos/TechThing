package com.learning.model;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;

    private String role_desc;

    private String role_name;



//    @OneToMany(mappedBy = "role")
//    private Set<UserRole> userRoles;

    public Role() {
        super();
    }


}
