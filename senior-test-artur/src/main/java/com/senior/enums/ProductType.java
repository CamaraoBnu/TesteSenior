package com.senior.enums;

import lombok.ToString;

@ToString
public enum ProductType {
    PRODUCT("Product"),
    SERVICE("Service");

    public final String label;

    private ProductType(String label) {
        this.label = label;
    }
}
