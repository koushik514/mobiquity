package com.mobiquityinc.exception;

public class APIException extends Exception {

    private String errorData;

    public APIException( String errorData)
    {
        this.errorData = errorData;
    }

    public String getErrorData() {
        return errorData;
    }

    public void setErrorData(String errorData) {
        this.errorData = errorData;
    }
}

