package ru.gb.wintermarket.api.exceptions;

import java.util.List;
import java.util.stream.Collectors;


public class ValidationException extends RuntimeException{
    private List<String> errorFieldMessages;

    public List<String> getErrorFieldMessages() {
        return errorFieldMessages;
    }

    public void setErrorFieldMessages(List<String> errorFieldMessages) {
        this.errorFieldMessages = errorFieldMessages;
    }

    public ValidationException(List<String> errorFieldMessages){
        super(errorFieldMessages.stream().collect(Collectors.joining(",  ")));
        this.errorFieldMessages = errorFieldMessages;
    }
}

