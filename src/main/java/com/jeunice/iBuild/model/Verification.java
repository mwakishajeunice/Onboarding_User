package com.jeunice.iBuild.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="verification")
public class Verification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;

    @NonNull
    private LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH , CascadeType.PERSIST, CascadeType.MERGE})
    private AppUser appUser;

    public Verification(){}
    public Verification(String code, @NonNull LocalDateTime expiredAt, AppUser appUser) {
        this.code = code;
        this.expiredAt = expiredAt;
        this.appUser = appUser;
    }
}
