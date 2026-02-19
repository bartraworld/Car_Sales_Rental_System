package bartra.world.carsalesrentalsystem.controllers;

import bartra.world.carsalesrentalsystem.models.BaseModel;
import bartra.world.carsalesrentalsystem.models.IdModel;
import bartra.world.carsalesrentalsystem.models.cars.CarResponse;
import bartra.world.carsalesrentalsystem.models.customers.CustomerResponse;
import bartra.world.carsalesrentalsystem.models.customers.CustomerToSaveRequest;
import bartra.world.carsalesrentalsystem.models.sales.CustomerSaleResponse;
import bartra.world.carsalesrentalsystem.services.CustomerService;
import bartra.world.carsalesrentalsystem.services.SaleService;
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

    private final CustomerService customerService;
    private final SaleService saleService;


    public CustomerController(CustomerService customerService, SaleService saleService) {
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @GetMapping("")
    public BaseModel<List<CustomerResponse>> getAllCustomers() {
        return new BaseModel<>(
                "success",
                "customers retrieved successfully",
                customerService.getAllCustomers().stream().map(
                        (customer) -> new CustomerResponse(
                                customer.getId(),
                                customer.getFirstName(),
                                customer.getLastName(),
                                customer.getDateOfBirth(),
                                customer.getPhoneNumber(),
                                customer.getEmail(),
                                customer.getTaxCode(),
                                customer.getAddress(),
                                customer.getBadPayer()
                        )
                ).toList()
        );
    }

    @PostMapping("/customer")
    public BaseModel<IdModel> addCustomer(@RequestBody CustomerToSaveRequest customerToSaveRequest) {
        var id = customerService.addCustomer(customerToSaveRequest);
        return new BaseModel<>("success", "Customer added successfully", new IdModel(id));

    }

    @GetMapping("/customer/{id}")
    public BaseModel<CustomerResponse> getCustomerById(@PathVariable Long id) {
        return new BaseModel<>("success", "Customer retrieved successfully", new CustomerResponse(
                customerService.getCustomerById(id).getId(),
                customerService.getCustomerById(id).getFirstName(),
                customerService.getCustomerById(id).getLastName(),
                customerService.getCustomerById(id).getDateOfBirth(),
                customerService.getCustomerById(id).getPhoneNumber(),
                customerService.getCustomerById(id).getEmail(),
                customerService.getCustomerById(id).getTaxCode(),
                customerService.getCustomerById(id).getAddress(),
                customerService.getCustomerById(id).getBadPayer()
        ));
    }

//    Car getCarFromSale(Sale sale){
//        return sale.getCarSold();
//    }

    @GetMapping("/customer/{id}/sales/")
    public BaseModel<List<CustomerSaleResponse>> getSalesToCustomer(@PathVariable Long id) {
        var customer = customerService.getCustomerById(id);
        var sales = saleService.getSalesByCustomer(customer);
        // var cars = sales.stream().map(sale -> getCarFromSale(sale)).toList();
//        questo qui giù è uguale a questo qua su
//        List<Car> carssoldssasa = new ArrayList<>();
//        for(var sale:sales){
//            carssoldssasa.add(getCarFromSale(sale);
//        }

        return new BaseModel<>(
                "success",
                "Sales retrieved successfully",
                sales.stream().map(
                        (sale) -> new CustomerSaleResponse(
                                sale.getId(),
                                new CarResponse(
                                        sale.getCarSold().getId(),
                                        sale.getCarSold().getMake(),
                                        sale.getCarSold().getModel(),
                                        sale.getCarSold().getYear(),
                                        sale.getCarSold().getCurrentPrice()
                                ),
                                sale.getSaleDate().toString(),
                                sale.getSalePrice()
                        )
                ).toList()
        );
    }
}
