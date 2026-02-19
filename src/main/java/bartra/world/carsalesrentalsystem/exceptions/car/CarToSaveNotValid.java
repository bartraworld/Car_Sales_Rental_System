package bartra.world.carsalesrentalsystem.exceptions.car;

import bartra.world.carsalesrentalsystem.exceptions.BaseException;
import bartra.world.carsalesrentalsystem.models.cars.CarToSaveRequest;

public class CarToSaveNotValid extends BaseException {
    public CarToSaveNotValid(CarToSaveRequest carToSaveRequest) {
        super(
                "error",
                "Car to save is not valid",
                "The car to save request is not valid. Please check the details and try again. Car to save request: " + carToSaveRequest.toString()
        );
    }
}
