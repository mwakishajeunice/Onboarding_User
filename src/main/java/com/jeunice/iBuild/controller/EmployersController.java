package com.jeunice.iBuild.controller;

import com.jeunice.iBuild.model.Employers;
import com.jeunice.iBuild.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployersController {

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("/get-all-employers")
    public List<Employers> getAllEmployers(){
        return  employerRepository.findAll();
    }

    @GetMapping("/getEmployerByCode/{employerCode}")
    public Employers getEmployerByCode(@PathVariable("employerCode") Integer employerCode){
        return employerRepository.findById(employerCode).get();
    }

    @DeleteMapping("/deleteEmployer/{employerCode}")
    public boolean deleteEmployerByCode(@PathVariable("employerCode") Integer employerCode){
        if (!employerRepository.findById(employerCode).equals(Optional.empty())){
            employerRepository.deleteById(employerCode);
            return  true;
        }
        return false;
    }

    @PutMapping("/updateEmployer/{employerCode}")
    public ResponseEntity<?> updateEmployer(@PathVariable("employerCode") Integer employerCode, @RequestBody Map<String, String> body ){

        Optional<Employers> optionalEmployers = employerRepository.findById(employerCode);
        if(optionalEmployers.isPresent()){
            Employers employers = optionalEmployers.get();

            if(body.get("employerAddress") != null) {
                employers.setEmployerAddress(body.get("employerAddress"));
            }
            employers.setEmployerName(body.get("employerName"));

            if(body.get("retirementAge") != null){
                employers.setRetirementAge(Long.valueOf(body.get("retirementAge")));
            }

            Employers save = employerRepository.save(employers);
            return ResponseEntity.ok(save);


        } else {
            throw new RuntimeException("Employer does not exist");
        }

    }

    @PostMapping("/addEmployer")
    public Employers createNewEmployer(@RequestBody Map<String, String> bodyRequest){

        String employerAddress = bodyRequest.get("employerAddress");
        Long retirementAge = Long.valueOf(bodyRequest.get("retirementAge"));
        String employerName = bodyRequest.get("employerName");

        Employers employers = new Employers(employerName,employerAddress,retirementAge);
        return employerRepository.save(employers);

    }

}
