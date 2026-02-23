package bartra.world.carsalesrentalsystem.controllers;

import bartra.world.carsalesrentalsystem.models.BaseModel;
import bartra.world.carsalesrentalsystem.models.IdModel;
import bartra.world.carsalesrentalsystem.models.cars.CarResponse;
import bartra.world.carsalesrentalsystem.models.cars.CarToSaveRequest;
import bartra.world.carsalesrentalsystem.models.cars.CarToUpdateRequest;
import bartra.world.carsalesrentalsystem.services.CarService;
import org.springframework.web.bind.annotation.*;

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
        var cars = carService.getAllCars();

        return new BaseModel<>(
                "success",
                "Cars retrieved successfully",
                cars.stream().map(car -> new CarResponse(
                        car.getId(),
                        car.getMake(),
                        car.getModel(),
                        car.getYear(),
                        car.getCurrentPrice()
                )).toList()
        );
    }

    @PostMapping("/car")
    public BaseModel<IdModel> addCar(@RequestBody CarToSaveRequest req) {
        var id = carService.addCar(req);
        return new BaseModel<>("success", "Car added successfully. Car ID: ", new IdModel(id));

    }

    @GetMapping("/car/{id}")
    public BaseModel<CarResponse> getCarById(@PathVariable Long id) {
        var car = carService.getCar(id);
        return new BaseModel<>(
                "success",
                "Car retrieved successfully",
                new CarResponse(
                        car.getId(),
                        car.getMake(),
                        car.getModel(),
                        car.getYear(),
                        car.getCurrentPrice()
                )
        );
    }

    @DeleteMapping("/car/{id}")
    public BaseModel <CarResponse> deleteCar(@PathVariable Long id){
        var car =carService.deleteCar(id);

        return new BaseModel<>("Success", ", Car deleted successfully.",
                new CarResponse(car.getId(),
                        car.getMake(),
                        car.getModel(),
                        car.getYear(),
                        car.getCurrentPrice()
                )
        );

    }

    @PutMapping("/car/{id}")
    public BaseModel<CarResponse> updateCar(@PathVariable Long id, @RequestBody CarToUpdateRequest updateReq) {
        var car = carService.updateCar(id, updateReq);
        return new BaseModel<>(
                "success",
                "Car updated successfully",
                new CarResponse(
                        car.getId(),
                        car.getMake(),
                        car.getModel(),
                        car.getYear(),
                        car.getCurrentPrice()
                )
        );
    }


}
