package com.jeunice.iBuild.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "appUser")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Levels levels;
    private String phoneNumber;
    private String password;

    private boolean enabled = false;

    public AppUser(){}

    public AppUser(String email, String firstName, String lastName, Levels levels, String phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.levels = levels;
        this.phoneNumber = phoneNumber;
    }


}
