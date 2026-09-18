package ru.pulsarmn.messenger.user.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        return ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
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

    @ExceptionHandler(IllegalArgumentException.class)
    ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Invalid input data", ex);
        return ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.warn("Invalid method argument(-s)", ex);
        return ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    ProblemDetail handleException(Exception ex) {
        log.error("An unexpected error occurred", ex);
        return ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
