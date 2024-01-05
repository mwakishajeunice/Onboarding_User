package com.jeunice.iBuild.dto;

import lombok.Getter;

@Getter
public class ResetPasswordDTO {

    private String code;
    private String password;

    public boolean checkFields(){
       return  ((code != null || !code.isEmpty())
                && (password !=null || !password.isEmpty()));
    }
}
