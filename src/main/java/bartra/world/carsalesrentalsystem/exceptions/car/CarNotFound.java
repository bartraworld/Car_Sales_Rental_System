package bartra.world.carsalesrentalsystem.exceptions.car;

import bartra.world.carsalesrentalsystem.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarNotFound extends BaseException {
    public CarNotFound(Long carId) {
        super(
                "error",
                "Car was not found",
                "The car with the id " + carId + " was not found. Please check the id and try again."
        );
    }
}
