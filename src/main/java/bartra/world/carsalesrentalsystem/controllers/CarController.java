package bartra.world.carsalesrentalsystem.controllers;

import bartra.world.carsalesrentalsystem.models.BaseModel;
import bartra.world.carsalesrentalsystem.models.IdModel;
import bartra.world.carsalesrentalsystem.models.cars.CarResponse;
import bartra.world.carsalesrentalsystem.models.cars.CarToSaveRequest;
import bartra.world.carsalesrentalsystem.services.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    final
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("")
    public BaseModel<List<CarResponse>> getCars() {
        return new BaseModel<>("success", "Cars retrieved successfully", carService.getAllCars());
    }

    @PostMapping("/car")
    public BaseModel<IdModel> addCar(@RequestBody CarToSaveRequest carToSaveRequest) {
        var idModel = carService.addCar(carToSaveRequest);
        return new BaseModel<>("success", "Car added successfully", idModel);

    }

    @GetMapping("/car/{id}")
    public BaseModel<CarResponse> getCarById(@PathVariable Long id) {
        return new BaseModel<>("success", "Car retrieved successfully", carService.getCar(id));
    }

}
