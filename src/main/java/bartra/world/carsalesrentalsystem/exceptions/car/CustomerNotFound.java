package bartra.world.carsalesrentalsystem.exceptions.car;

import bartra.world.carsalesrentalsystem.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class CustomerNotFound extends BaseException {
    public CustomerNotFound(Long customerId) {
        super(
                HttpStatus.NOT_FOUND,
                "error",
                "Customer was not found",
                "The customer with the id " + customerId + " was not found. Please check the id and try again."
        );
    }
}