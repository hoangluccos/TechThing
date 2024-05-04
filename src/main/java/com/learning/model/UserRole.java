package com.learning.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@Table(name = "users_roles")
//public class UserRole {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @ManyToOne
//    @JoinColumn(name = "userId", referencedColumnName = "username")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "roleId", referencedColumnName = "role_id")
//    private Role role;
//
//
//    public UserRole() {
//
//    }
//}
