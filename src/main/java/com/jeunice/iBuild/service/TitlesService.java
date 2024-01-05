package com.jeunice.iBuild.service;

import com.jeunice.iBuild.dto.TitlesDTO;
import com.jeunice.iBuild.model.Titles;
import com.jeunice.iBuild.repository.TitlesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TitlesService {
    @Autowired
    private TitlesRepository titlesRepository;

    public Object add(TitlesDTO titlesDTO) {

        Map<String, String> response = new HashMap<>();
        Titles titles = new Titles(
                titlesDTO.getTitleName(),
                titlesDTO.getDescription(),
                titlesDTO.getEducation()
        );
        titlesRepository.save(titles);
        response.put("status", "0");
        response.put("message", "success");

        return response;

    }
}
