package br.com.fiap.prontuario.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.prontuario.models.RestErrors;
import br.com.fiap.prontuario.models.StatusError;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {
    Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<RestErrors>> constraintViolationExceptionHandler(ConstraintViolationException e){
        log.error("Erro capturado pelo Handler");
        List<RestErrors> erros = new ArrayList<>();

        e.getConstraintViolations().forEach(v -> erros.add(new RestErrors(v.getPropertyPath().toString(),  v.getMessage())));

        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StatusError> ResponseStatusExceptionHandler(ResponseStatusException e){
        return ResponseEntity.status(e.getStatusCode()).body(new StatusError(e.getStatusCode().value(), e.getBody().getDetail()));
    }

}
