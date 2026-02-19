package bartra.world.carsalesrentalsystem.controllers;

import bartra.world.carsalesrentalsystem.models.BaseModel;
import bartra.world.carsalesrentalsystem.models.sales.SaleCreatedResponse;
import bartra.world.carsalesrentalsystem.models.sales.SaleToCreate;
import bartra.world.carsalesrentalsystem.services.CarService;
import bartra.world.carsalesrentalsystem.services.CustomerService;
import bartra.world.carsalesrentalsystem.services.SaleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/sales")
public class SalesController {


    private final CarService carService;
    private final SaleService saleService;
    private final CustomerService customerService;

    public SalesController(CarService carService, SaleService saleService, CustomerService customerService) {
        this.carService = carService;
        this.saleService = saleService;
        this.customerService = customerService;
    }

    @PostMapping("/sale")
    public BaseModel<SaleCreatedResponse> makeSale(SaleToCreate saleToCreate) {
        var car = carService.getCar(saleToCreate.carId());
        var customer = customerService.getCustomerById(saleToCreate.customerId());
        var saleId = saleService.createSale(car, customer, saleToCreate.salePrice(), saleToCreate.saleDate());

        return new BaseModel<>("success", "Sale made successfully", new SaleCreatedResponse(saleId));
    }
}
