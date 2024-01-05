package com.jeunice.iBuild.dto;

import com.jeunice.iBuild.model.Levels;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public class AppUserDTO {

    private String email;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Levels levels;
    private String phoneNumber;
    private String password;

    public boolean checkUserFields() {
        return (email != null || !email.isEmpty()) &&
                (firstName != null || !firstName.isEmpty()) &&
                (lastName != null || !lastName.isEmpty()) &&
                (phoneNumber != null || !phoneNumber.isEmpty()) &&
                (password != null || !password.isEmpty());
    }

}
