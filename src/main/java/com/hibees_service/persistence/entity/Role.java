package com.hibees_service.persistence.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

//
//    @ManyToMany(mappedBy = "roles")
//    private Collection<User> users;
//
//    @ManyToMany
//    @JoinTable(
//            name = "roles_privileges",
//            joinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    na    me = "privilege_id", referencedColumnName = "id"))
 //   private Collection<Privilege> privileges;

}

//EMAILPASSWORD=efxl eksq rvst texq;EMAILUSER=jeremiahokorieimo@gmail.com;PASSWORD=Jimohab@123;USERNAME=root