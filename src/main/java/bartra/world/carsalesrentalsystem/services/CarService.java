package bartra.world.carsalesrentalsystem.services;

import bartra.world.carsalesrentalsystem.entities.Car;
import bartra.world.carsalesrentalsystem.exceptions.car.CarNotFound;
import bartra.world.carsalesrentalsystem.models.cars.CarToSaveRequest;
import bartra.world.carsalesrentalsystem.repositories.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarService {
    final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car getCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFound(id));
        return new Car(
                car.getId(),
                car.getMake(),
                car.getModel(),
                car.getPlate(),
                car.getYear(),
                car.getCurrentPrice()
        );
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Long addCar(CarToSaveRequest carToSaveRequest) {
        Car car = Car.builder()
                .make(carToSaveRequest.make())
                .model(carToSaveRequest.model())
                .year(carToSaveRequest.year())
                .currentPrice(carToSaveRequest.currentPrice())
                .build();

        car = carRepository.save(car);
        return car.getId();
    }
}
