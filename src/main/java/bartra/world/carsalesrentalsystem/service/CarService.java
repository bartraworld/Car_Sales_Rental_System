package bartra.world.carsalesrentalsystem.service;

import bartra.world.carsalesrentalsystem.entity.Car;
import bartra.world.carsalesrentalsystem.model.IdModel;
import bartra.world.carsalesrentalsystem.model.car.CarToSaveRequest;
import bartra.world.carsalesrentalsystem.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarService {
    final
    CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public IdModel addCar(CarToSaveRequest carToSaveRequest) {
        Car car = Car.builder()
                .make(carToSaveRequest.make())
                .model(carToSaveRequest.model())
                .year(carToSaveRequest.year())
                .currentPrice(carToSaveRequest.currentPrice())
                .build();

        car = carRepository.save(car);
        return new IdModel(car.getId());
    }
}
