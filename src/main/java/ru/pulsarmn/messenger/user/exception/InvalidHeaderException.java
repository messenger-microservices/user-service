package ru.pulsarmn.messenger.user.exception;


public class InvalidHeaderException extends RuntimeException {

    private final String value;

    public InvalidHeaderException(String message, String value) {
        super(message);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
