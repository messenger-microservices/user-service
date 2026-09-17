package ru.pulsarmn.messenger.user.exception;


public class UnauthenticatedException extends RuntimeException {

    public UnauthenticatedException() {
    }

    public UnauthenticatedException(String message) {
        super(message);
    }
}
