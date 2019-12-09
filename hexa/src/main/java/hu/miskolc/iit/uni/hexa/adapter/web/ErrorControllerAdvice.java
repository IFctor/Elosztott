package hu.miskolc.iit.uni.hexa.adapter.web;

import hu.miskolc.iit.uni.hexa.domain.AuthorNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler({AuthorNotFoundException.class})
    public ResponseEntity<String> handleAuthorNotFoundException(AuthorNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
