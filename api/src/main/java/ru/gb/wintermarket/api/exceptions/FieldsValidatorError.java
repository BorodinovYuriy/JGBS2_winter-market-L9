package ru.gb.wintermarket.api.exceptions;

import java.util.List;

public class FieldsValidatorError {
    private List<String> errorFieldMessages;

    public FieldsValidatorError() {
    }

    public FieldsValidatorError(List<String> errorFieldMessages){
        this.errorFieldMessages = errorFieldMessages;
    }

    public List<String> getErrorFieldMessages() {
        return errorFieldMessages;
    }

    public void setErrorFieldMessages(List<String> errorFieldMessages) {
        this.errorFieldMessages = errorFieldMessages;
    }
}

