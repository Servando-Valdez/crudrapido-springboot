package com.example.crudrapido.exceptionAdvice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST).getBody();
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
//        List<String> errors = ex.getBindingResult().getFieldErrors()
//                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
//        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
//    }
//

    /**
     * This function return a error list map
     * @param errors
     * @return
     */
    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    private static Map<String, Map<String, String>> getErrorsMap(Map<String, String> errors) {
        Map<String, Map<String, String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }


//    private Map<String, List<String>> getErrorsMap(List<String> errors) {
//        Map<String, List<String>> errorResponse = new HashMap<>();
//        errorResponse.put("errors", errors);
//        return errorResponse;
//    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, Map<String, String>>> handleEmailDuplicate(DuplicateEmailException ex) {
        Map<String, String> errores = new HashMap<>();
        errores.put("email", ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errores), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleIdNotFound(IdNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(DuplicateEmailException.class)
//    public ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
//    }
}
