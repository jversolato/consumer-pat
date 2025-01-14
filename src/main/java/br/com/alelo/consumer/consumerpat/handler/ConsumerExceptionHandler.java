package br.com.alelo.consumer.consumerpat.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.alelo.consumer.consumerpat.exception.NotEnoughBalanceException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerExistException;
import br.com.alelo.consumer.consumerpat.exception.RestNotFoundException;

@ControllerAdvice
public class ConsumerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RestNotFoundException.class, ConsumerExistException.class})
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(
                ex,
                ex.getMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    protected ResponseEntity<Object> handleNotEnoughBalance(final NotEnoughBalanceException ex, final WebRequest request) {
        return handleExceptionInternal(
                ex,
                "Not enough balance to process. Current: ".concat(String.valueOf(ex.getCurrentBalance())),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(
                ex,
                ex.getMessage(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

}