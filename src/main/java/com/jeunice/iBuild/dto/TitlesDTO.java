package com.jeunice.iBuild.dto;

import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class TitlesDTO {

    private String titleName;
    private String description;
    private String education;

    public boolean checkFields(){
        return ( titleName != null && !titleName.isEmpty()) &&
                ( description != null && !description.isEmpty()) &&
                ( education != null && !education.isEmpty());

    }
}
