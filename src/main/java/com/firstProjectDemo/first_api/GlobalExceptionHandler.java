package com.firstProjectDemo.first_api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFound(OrderNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidOrderStateException.class)
    public ResponseEntity<String> invalidOrderStateException(InvalidOrderStateException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<String> DoctorNotFoundException(DoctorNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> MethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return new ResponseEntity<>("Invalid input data for the following method",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProjectNotFoundException.class )
        public ResponseEntity<String> ProjectNotFoundException(ProjectNotFoundException ex){
            return new ResponseEntity<>("The said relevant project not found",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BudgetExceededException.class)
        public ResponseEntity<String> BudgetExceededException(BudgetExceededException ex){
            return new ResponseEntity<>("Budget exceeded for a particular project",HttpStatus.PAYMENT_REQUIRED);
        }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<String> WalletNotFoundException(WalletNotFoundException ex){
        return new ResponseEntity<>("Wallet not found",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookUnavailableException.class)
    public ResponseEntity<String> BookUnavailableException(BookUnavailableException ex){
        return new ResponseEntity<>("Book not found",HttpStatus.BAD_REQUEST);
    }


}
