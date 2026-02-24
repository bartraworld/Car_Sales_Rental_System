package bartra.world.carsalesrentalsystem;

import bartra.world.carsalesrentalsystem.exceptions.BaseException;
import bartra.world.carsalesrentalsystem.models.BaseModel;
import bartra.world.carsalesrentalsystem.models.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseModel<ErrorResponse>> handleException(BaseException e) {
        log.error("An error occurred({}): {}", e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new BaseModel<>(
                        e.getStatus(),
                        e.getMessage(),
                        new ErrorResponse(e.getDetails())
                ),
                e.getCode()
        );
    }

    //Controller validations
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public BaseModel<Map<String, List<String>>> handleValidationException(MethodArgumentNotValidException e) {
        log.error("DTO Validation error: {}", e.getMessage());
        Map<String, List<String>> errors = e.getBindingResult().getAllErrors().stream()
                .filter(error -> error.getDefaultMessage() != null)
                .collect(
                        Collectors.toMap(
                                error -> ((FieldError) error).getField(),
                                error -> List.of(error.getDefaultMessage()),
                                (existing, replacement) -> {
                                    var merged = new ArrayList<>(existing);
                                    merged.addAll(replacement);
                                    return merged;
                                }
                        )
                );
        return new BaseModel<>(
                "error",
                "Validation failed. Please check your input and try again.",
                errors
        );
    }

    // parameters validation
    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public BaseModel<Map<String, List<String>>> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("Constraint Validation error: {}", e.getMessage());
        return new BaseModel<>(
                "error",
                "Validation failed. Please check your input and try again.",
                e.getConstraintViolations().stream()
                        .collect(
                                Collectors.toMap(
                                        violation -> violation.getPropertyPath().toString(),
                                        (ConstraintViolation<?> violation) -> List.of(violation.getMessage()),
                                        //for multiple error handling
                                        (existing, replacement) -> {
                                            var merged = new ArrayList<>(existing);
                                            merged.addAll(replacement);
                                            return merged;
                                        }
                                )
                        )
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
