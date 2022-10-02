package co.com.meli.mutantsMeli.domain.utils;

public class MutantException extends Exception{
    private final String errorCode;
    private final String description;

    public MutantException(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public String getErrorCode() {return errorCode;}
    public String getDescription() {return description;}
}
