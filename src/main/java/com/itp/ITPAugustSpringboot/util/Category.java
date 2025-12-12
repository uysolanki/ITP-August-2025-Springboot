package com.itp.ITPAugustSpringboot.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {

    MENS_CLOTHING("men's clothing"),
    WOMENS_CLOTHING("women's clothing"),
    ELECTRONICS("electronics"),
    JEWELLERY("jewelery");

    private final String label;

    private Category(String label) 
    {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}