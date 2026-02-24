package bartra.world.carsalesrentalsystem.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class BaseException extends RuntimeException {
    protected HttpStatusCode code;
    protected String status;
    protected String details;

    public BaseException(HttpStatusCode code, String status, String message, String details) {
        super(message);
        this.code = code;
        this.status = status;
        this.details = details;
    }
}
