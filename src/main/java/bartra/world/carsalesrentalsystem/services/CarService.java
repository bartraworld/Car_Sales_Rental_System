package bartra.world.carsalesrentalsystem.services;

import bartra.world.carsalesrentalsystem.entities.Car;
import bartra.world.carsalesrentalsystem.exceptions.car.CarNotFound;
import bartra.world.carsalesrentalsystem.models.cars.CarToSaveRequest;
import bartra.world.carsalesrentalsystem.models.cars.CarToPatchRequest;
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
        return carRepository.findById(id).orElseThrow(() -> new CarNotFound(id));

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
                .plate(req.plate())
                .build();

        car = carRepository.save(car);

        return car.getId();
    }

    public Car deleteCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFound(id));
        carRepository.delete(car);
        return car;
    }

    public Car patchCar(Long id, CarToPatchRequest patchReq) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFound(id));
        if(patchReq.make()!=null && !patchReq.make().isBlank()) car.setMake(patchReq.make());
        if(patchReq.model()!=null && !patchReq.model().isBlank()) car.setModel(patchReq.model());
        if(patchReq.year()!=null) car.setYear(patchReq.year());
        if(patchReq.currentPrice()!=null) car.setCurrentPrice(patchReq.currentPrice());
        if(patchReq.plate()!=null && !patchReq.plate().isBlank()) car.setPlate(patchReq.plate());
        return carRepository.save(car);
    }

}
