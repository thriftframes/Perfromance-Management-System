//package com.pms.entity;
//
//
//import com.pms.entity.User;
//import jakarta.persistence.*;
//import lombok.Data;
//import java.util.Set;
//
//@Entity
//@Data
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    private UserRole name;
//
//    @ManyToMany(mappedBy = "roles") // Corrected mappedBy attribute
//    private Set<User> users;
//
//    // Constructors, Getters, and Setters
//}