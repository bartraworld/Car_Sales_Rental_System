package bartra.world.carsalesrentalsystem;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
     @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        // Log the exception (you can use a logging framework here)
        System.err.println("An error occurred: " + e.getMessage());
        // Return a generic error message to the client
        return "An unexpected error occurred. Please try again later.";
    }
}
