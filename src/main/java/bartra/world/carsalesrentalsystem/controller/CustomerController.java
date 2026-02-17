package bartra.world.carsalesrentalsystem.controller;

import bartra.world.carsalesrentalsystem.model.BaseModel;
import bartra.world.carsalesrentalsystem.model.IdModel;
import bartra.world.carsalesrentalsystem.model.customer.CustomerResponse;
import bartra.world.carsalesrentalsystem.model.customer.CustomerToSaveRequest;
import bartra.world.carsalesrentalsystem.service.CustomerService;
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

    @GetMapping("/customers")
    public BaseModel<List<CustomerResponse>> getAllCustomers() {
        return new BaseModel<>("success", "customers retrieved successfully",customerService.getAllCustomers());
    }

    @PostMapping("customer")
    public BaseModel<IdModel> addCustomer(@RequestBody CustomerToSaveRequest customerToSaveRequest) {
        var idModel = customerService.addCustomer(customerToSaveRequest);
        return new BaseModel<>("success", "Customer added successfully", idModel);

    }

    @GetMapping("/{id}")
    public BaseModel<CustomerResponse> getCustomerById(@PathVariable Long id) {
        return new BaseModel<>("success", "Customer retrieved successfully", customerService.getCustomerById(id));
    }
}
