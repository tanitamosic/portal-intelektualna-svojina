package com.xmlprojekat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class XMLDto {
    private String text;

    public XMLDto(String text) {
        this.text = text;
    }

    public XMLDto() {
    }
}
