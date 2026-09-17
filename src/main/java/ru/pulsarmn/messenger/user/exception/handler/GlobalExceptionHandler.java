package ru.pulsarmn.messenger.user.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.pulsarmn.messenger.user.exception.InvalidHeaderException;
import ru.pulsarmn.messenger.user.exception.UnauthenticatedException;
import ru.pulsarmn.messenger.user.exception.UserNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    ProblemDetail handleUserNotFoundException(UserNotFoundException ex) {
        log.warn("User with some id not found", ex);
        return ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    ProblemDetail handleUnauthenticated(UnauthenticatedException ex) {
        log.warn("The user is not authenticated", ex);
        return ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidHeaderException.class)
    ProblemDetail handleInvalidHeader(InvalidHeaderException ex) {
        log.warn("Invalid user id header", ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid user id");
    }
}
