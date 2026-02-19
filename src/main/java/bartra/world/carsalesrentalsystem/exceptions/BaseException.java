package bartra.world.carsalesrentalsystem.exceptions;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    protected String status;
    protected String details;

    public BaseException(String status, String message, String details) {
        super(message);
        this.status = status;
        this.details = details;
    }
}
