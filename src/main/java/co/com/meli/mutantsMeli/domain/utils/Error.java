package co.com.meli.mutantsMeli.domain.utils;

import java.util.List;

@Generated
public class Error{
    private int errorCode;
    private String error;
    private String errorMessage;
    private List<String> fieldErrors;

    public Error(String message, List<String> fieldErrors ) {
        this.errorMessage = message;
        this.fieldErrors = fieldErrors;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}

