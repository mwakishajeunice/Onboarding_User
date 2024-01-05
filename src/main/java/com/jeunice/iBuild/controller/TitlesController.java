package com.jeunice.iBuild.controller;

import com.jeunice.iBuild.dto.TitlesDTO;
import com.jeunice.iBuild.model.Titles;
import com.jeunice.iBuild.repository.TitlesRepository;
import com.jeunice.iBuild.service.TitlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TitlesController {

    @Autowired
    private TitlesRepository titlesRepository;

    @Autowired
    private TitlesService titlesService;

    @GetMapping("/allTitles")
    public List <Titles> getTitles(){
        return titlesRepository.findAll();
    }

@PostMapping("/addTitles")
    public ResponseEntity<?>createTitles(@RequestBody TitlesDTO titlesDTO){

        if(!titlesDTO.checkFields()){
            Map<String, String> response = new HashMap<>();
            response.put("message","bad request");
            response.put("status","400");
            return  new ResponseEntity<>( response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(titlesService.add(titlesDTO),HttpStatus.CREATED);

}

}
