package com.senior.enums;

import lombok.ToString;

@ToString
public enum CartStatus {
    OPEN("Open"),
    CLOSED("Closed");

    public final String label;

    private CartStatus(String label) {
        this.label = label;
    }
}
