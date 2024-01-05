package com.jeunice.iBuild.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "titles")
public class Titles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="titleCode")
    private Long titleCode;

    @Column(name="titleName")

    private String titleName;

    @Column(name = "description")
    private String description;

    @Column(name = "education")

    private String education;

    public Titles(){}

    public Titles(Long titleCode, String titleName){
        this.titleCode = titleCode;
        this.titleName = titleName;
    }

    public Titles(String titleName, String description, String education) {
        this.titleName = titleName;
        this.description = description;
        this.education = education;
    }
}
