package bartra.world.carsalesrentalsystem.service;

import bartra.world.carsalesrentalsystem.entity.Car;
import bartra.world.carsalesrentalsystem.model.IdModel;
import bartra.world.carsalesrentalsystem.model.car.CarResponse;
import bartra.world.carsalesrentalsystem.model.car.CarToSaveRequest;
import bartra.world.carsalesrentalsystem.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarService {
    final
    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream().map(car -> new CarResponse(
                        car.getId(),
                        car.getMake(),
                        car.getModel(),
                        car.getYear(),
                        car.getCurrentPrice()
                )
        ).toList();
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
