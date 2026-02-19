package bartra.world.carsalesrentalsystem.controllers;

import bartra.world.carsalesrentalsystem.models.BaseModel;
import bartra.world.carsalesrentalsystem.models.IdModel;
import bartra.world.carsalesrentalsystem.models.customers.CustomerResponse;
import bartra.world.carsalesrentalsystem.models.customers.CustomerToSaveRequest;
import bartra.world.carsalesrentalsystem.services.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")

public class CustomerController {

    final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public BaseModel<List<CustomerResponse>> getAllCustomers() {
        return new BaseModel<>("success", "customers retrieved successfully", customerService.getAllCustomers());
    }

    @PostMapping("/customer")
    public BaseModel<IdModel> addCustomer(@RequestBody CustomerToSaveRequest customerToSaveRequest) {
        var idModel = customerService.addCustomer(customerToSaveRequest);
        return new BaseModel<>("success", "Customer added successfully", idModel);

    }

    @GetMapping("/customer/{id}")
    public BaseModel<CustomerResponse> getCustomerById(@PathVariable Long id) {
        return new BaseModel<>("success", "Customer retrieved successfully", customerService.getCustomerById(id));
    }
}
