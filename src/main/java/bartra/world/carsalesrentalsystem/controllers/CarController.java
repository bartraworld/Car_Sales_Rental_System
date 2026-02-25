package bartra.world.carsalesrentalsystem.controllers;

import bartra.world.carsalesrentalsystem.models.BaseModel;
import bartra.world.carsalesrentalsystem.models.IdModel;
import bartra.world.carsalesrentalsystem.models.cars.CarResponse;
import bartra.world.carsalesrentalsystem.models.cars.CarToSaveRequest;
import bartra.world.carsalesrentalsystem.models.cars.CarToPatchRequest;
import bartra.world.carsalesrentalsystem.services.CarService;
import jakarta.validation.Valid;
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
                        car.getCurrentPrice(),
                        car.getPlate())).toList()
        );
    }

    @PostMapping("/car")
    public BaseModel<IdModel> addCar(@Valid @RequestBody CarToSaveRequest req) {
        var id = carService.addCar(req);
        return new BaseModel<>("success", "Car added successfully. Car ID: ", new IdModel(id));

    }

    @GetMapping("/car/{id}")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Car retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Car not found")
    })
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
                        car.getCurrentPrice(),
                        car.getPlate())
        );
    }

    @DeleteMapping("/car/{id}")
    public BaseModel<CarResponse> deleteCar(@PathVariable Long id) {
        var car = carService.deleteCar(id);

        return new BaseModel<>("Success", ", Car deleted successfully.",
                new CarResponse(car.getId(),
                        car.getMake(),
                        car.getModel(),
                        car.getYear(),
                        car.getCurrentPrice(),
                        car.getPlate())
        );

    }

    @PatchMapping("/car/{id}")
    public BaseModel<CarResponse> patchCar(@PathVariable Long id, @RequestBody CarToPatchRequest patchReq) {
        var car = carService.patchCar(id, patchReq);
        return new BaseModel<>(
                "success",
                "Car updated successfully",
                new CarResponse(
                        car.getId(),
                        car.getMake(),
                        car.getModel(),
                        car.getYear(),
                        car.getCurrentPrice(),
                        car.getPlate()
                )
        );
    }


}
