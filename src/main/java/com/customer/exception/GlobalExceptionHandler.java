package com.customer.exception;

import com.customer.entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleCustomerNotFound(CustomerNotFoundException ex){
        ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Customer Not Found.");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("customerId", ex.getCustomerId());
        problemDetail.setProperty("errorCode", "CUSTOMER_NOT_FOUND");
        //problemDetail.setProperty("timestamp",Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleCustomerAlreadyExists(CustomerAlreadyExistsException ex){
        ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Customer Already Exists.");
        problemDetail.setDetail(ex.getMessage());
        //problemDetail.setProperty("timestamp",Instant.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGenericErrors(Exception ex){
        ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("INTERNAL SERVER ERROR.");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("timestamp",Instant.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail);
    }


}
