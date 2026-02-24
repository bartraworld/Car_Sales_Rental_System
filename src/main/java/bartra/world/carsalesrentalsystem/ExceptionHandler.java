package bartra.world.carsalesrentalsystem;

import bartra.world.carsalesrentalsystem.exceptions.BaseException;
import bartra.world.carsalesrentalsystem.exceptions.car.CarNotFound;
import bartra.world.carsalesrentalsystem.models.BaseModel;
import bartra.world.carsalesrentalsystem.models.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CarNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseModel<String> handleCarNotFound(CarNotFound e) {
        return new BaseModel<>("error", e.getMessage(), null);
    }

    //Business Logic errors
    @org.springframework.web.bind.annotation.ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseModel<ErrorResponse> handleException(BaseException e) {
        log.error("An error occurred({}): {}", e.getClass().getName(), e.getMessage());
        return new BaseModel<>(
                e.getStatus(),
                e.getMessage(),
                new ErrorResponse(e.getDetails())
        );
    }

     //Controller validations
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public BaseModel<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        log.error("DTO Validation error: {}", e.getMessage());
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new BaseModel<>(
                "error",
                "Validation failed. Please check your input and try again.",
                errors
        );
    }

    // parameters validation
    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public BaseModel<Map<String, String>> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("Constraint Validation error: {}", e.getMessage());
        return new BaseModel<>(
                "error",
                "Validation failed. Please check your input and try again.",
                e.getConstraintViolations().stream()
                        .collect(Collectors.toMap(
                                violation -> violation.getPropertyPath().toString(),
                                ConstraintViolation::getMessage,
                                //for multiple error handling
                                (existing, replacement) -> existing
                        ))
        );
    }

    //Unexpected errors
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseModel<ErrorResponse> handleGenericException(Exception e) {
        log.error("Unexpected error({}): {}", e.getClass().getName(), e.getMessage());
        // e.printStackTrace();
        return new BaseModel<>(
                "error",
                "An unexpected error occurred. Please try again later.",
                new ErrorResponse(e.getMessage())
        );
    }
}
