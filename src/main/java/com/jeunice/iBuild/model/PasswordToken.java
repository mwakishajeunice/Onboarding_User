package com.jeunice.iBuild.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "PasswordToken")
@Getter
@Setter
public class PasswordToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
    private String code;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH , CascadeType.PERSIST, CascadeType.MERGE})

//    @JoinColumn(name = "")
    private AppUser appUser;

    public PasswordToken(){}

    public PasswordToken(LocalDateTime expiresAt, String code, AppUser appUser) {
        this.expiresAt = expiresAt;
        this.code = code;
        this.appUser = appUser;
    }
}
