package com.jeunice.iBuild.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Employers")
public class Employers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employer_code")
    private Integer employerCode;
    @Column(name = "employer_name")

    private String employerName;
    @Column(name = "employer_address")

    private String employerAddress;
    @Column(name = "retirement_age")

    private Long retirementAge;

    //Constructor
    public Employers(){}

    public Employers(String employerName, String employerAddress, Long retirementAge){
this.employerAddress = employerAddress;
this.employerName = employerName;
this.retirementAge = retirementAge;
    }

    //Setters
    public  void  setEmployerAddress(String employerAddress){
        this.employerAddress = employerAddress;
    }
    public void setEmployerName(String employerName){
this.employerName = employerName;
    }

     public void setRetirementAge(Long retirementAge){this.retirementAge = retirementAge;}

public String getEmployerName(){
    return employerName;
}

public String getEmployerAddress(){return  employerAddress;}

    public Integer getEmployerCode(){return employerCode;}

    public Long getRetirementAge(){
        return  retirementAge;
    }
}
