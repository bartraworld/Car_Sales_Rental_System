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

    public Long addCar(CarToSaveRequest req) {
        Car car = Car.builder()
                .make(req.make())
                .model(req.model())
                .year(req.year())
                .currentPrice(req.currentPrice())
                .build();

        car = carRepository.save(car);

        return car.getId();
    }
}
