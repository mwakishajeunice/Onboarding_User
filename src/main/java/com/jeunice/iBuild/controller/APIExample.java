package com.jeunice.iBuild.controller;
import com.jeunice.iBuild.model.Employers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIExample {

@GetMapping("/")
    public String getEmployer(){
    return "Jeunice Mwakisha";
}

@GetMapping("/employers")
    public Employers getEmployers() {
    Employers employers = new Employers();

    employers.setEmployerName("Jeunice Mwakisha");
    employers.setEmployerAddress("P.O BOX 123");
    employers.setRetirementAge(55L);

    return employers;
}

}
