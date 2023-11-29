package ru.clevertec.springboothw.exception.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.clevertec.springboothw.exception.ChannelNotFoundException;
import ru.clevertec.springboothw.exception.PersonNotFoundException;
import ru.clevertec.springboothw.exception.dto.ExceptionResponse;
import ru.clevertec.springboothw.exception.dto.ExceptionsMessages;
import java.sql.SQLException;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler{

    @ExceptionHandler(ChannelNotFoundException.class)
    public ResponseEntity<?> handlChannelNotFoundException(ChannelNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<?> handlPersonNotFoundException(PersonNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception){
        List<String> messages = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionsMessages(messages));
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handlePSQLException(SQLException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(exception.getMessage()));
    }
}
