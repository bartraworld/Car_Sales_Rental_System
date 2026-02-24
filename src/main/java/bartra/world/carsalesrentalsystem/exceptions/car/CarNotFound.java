package bartra.world.carsalesrentalsystem.exceptions.car;

import bartra.world.carsalesrentalsystem.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class CarNotFound extends BaseException {
    public CarNotFound(Long carId) {
        super(
                HttpStatus.NOT_FOUND,
                "error",
                "Car was not found",
                "The car with the id " + carId + " was not found. Please check the id and try again."
        );
    }
}
