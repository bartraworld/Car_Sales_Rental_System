package bartra.world.carsalesrentalsystem.exceptions.car;

import bartra.world.carsalesrentalsystem.model.car.CarToSaveRequest;

public class CarToSaveNotValid extends RuntimeException {
    public CarToSaveNotValid(CarToSaveRequest carToSaveRequest) {
        super(String.format("Car to save is not valid: %s", carToSaveRequest));
    }
}
