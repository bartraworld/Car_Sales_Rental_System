package bartra.world.carsalesrentalsystem;

import bartra.world.carsalesrentalsystem.exceptions.BaseException;
import bartra.world.carsalesrentalsystem.models.BaseModel;
import bartra.world.carsalesrentalsystem.models.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BaseException.class)
    public BaseModel<ErrorResponse> handleException(BaseException e) {
        log.error("An error occurred({}): {}", e.getClass().getName(), e.getMessage());
        return new BaseModel<>(
                e.getStatus(),
                e.getMessage(),
                new ErrorResponse(e.getDetails())
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public BaseModel<Map<String, String>> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("Validation error: {}", e.getMessage());
        return new BaseModel<>(
                "error",
                "Validation failed. Please check your input and try again.",
                e.getConstraintViolations().stream()
                        .collect(
                                java.util.stream.Collectors.toMap(
                                        violation -> violation.getPropertyPath().toString(),
                                        ConstraintViolation::getMessage
                                )
                        )
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public BaseModel<ErrorResponse> handleGenericException(Exception e) {
        log.error("An error occurred({}): {}", e.getClass().getName(), e.getMessage());
        return new BaseModel<>(
                "error",
                "An unexpected error occurred. Please try again later.",
                new ErrorResponse(e.getMessage())
        );
    }
}
