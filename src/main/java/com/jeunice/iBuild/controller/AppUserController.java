package com.jeunice.iBuild.controller;

import com.jeunice.iBuild.Utils.BasicResponse;
import com.jeunice.iBuild.dto.AppUserDTO;
import com.jeunice.iBuild.dto.ResetPasswordDTO;
import com.jeunice.iBuild.repository.AppUserRepository;
import com.jeunice.iBuild.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("/getUsers")
    public ResponseEntity <?> getAllUsers(){
        return ResponseEntity.ok(appUserService.getAll());
    }

    @PostMapping("/saveUser")
    public ResponseEntity <?> addUser(@RequestBody AppUserDTO appUserDTO){

        if(!appUserDTO.checkUserFields()){
            return ResponseEntity.ok(BasicResponse.failure("Missing Values"));
        }

        return ResponseEntity.ok(appUserService.signUp(appUserDTO));
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirmUser(@RequestParam String code){
        if(code == null || code.isEmpty()){
            return ResponseEntity.ok(BasicResponse.failure("Wrong Input"));
        }
        return ResponseEntity.ok(appUserService.confirmCode(code));
    }

    @GetMapping("/resendCode")
    public ResponseEntity<?> resendCode(@RequestParam String email){
        if(email == null || email.isEmpty()){
            return ResponseEntity.ok(BasicResponse.failure("email error"));
        }
        return ResponseEntity.ok(appUserService.resendCode(email));
    }

    @GetMapping("/resetPasswordCode")
    public ResponseEntity<?> sendCode(@RequestParam String email){
        if(email == null || email.isEmpty()){
            return ResponseEntity.ok(BasicResponse.failure("email missing!"));
        }
        return ResponseEntity.ok(appUserService.sendPassCode(email));
    }

    @PostMapping("/saveNewPassword")
    public ResponseEntity<?> savePassword(@RequestBody ResetPasswordDTO resetPasswordDTO){
       if(!resetPasswordDTO.checkFields()) {
           return ResponseEntity.ok(BasicResponse.failure("All fields are required!"));
       }

       return ResponseEntity.ok(appUserService.saveNewPassword(resetPasswordDTO));
    }

}
