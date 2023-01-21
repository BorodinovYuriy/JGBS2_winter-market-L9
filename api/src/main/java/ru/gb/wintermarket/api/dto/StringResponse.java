package ru.gb.wintermarket.api.dto;

public class StringResponse {
    public StringResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public StringResponse() {
    }

    private String value;
}
