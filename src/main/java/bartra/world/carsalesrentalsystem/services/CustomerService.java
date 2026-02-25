package bartra.world.carsalesrentalsystem.services;


import bartra.world.carsalesrentalsystem.entities.Customer;
import bartra.world.carsalesrentalsystem.exceptions.car.CustomerNotFound;
import bartra.world.carsalesrentalsystem.models.customers.CustomerToSaveRequest;
import bartra.world.carsalesrentalsystem.models.customers.CustomerToPatchRequest;
import bartra.world.carsalesrentalsystem.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class CustomerService {

    final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id " + id + " not found"));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Long addCustomer(CustomerToSaveRequest customerToSaveRequest) {
        Customer customer = Customer.builder()
                .firstName(customerToSaveRequest.firstName())
                .lastName(customerToSaveRequest.lastName())
                .dateOfBirth(customerToSaveRequest.dateOfBirth())
                .phoneNumber(customerToSaveRequest.phoneNumber())
                .email(customerToSaveRequest.email())
                .taxCode(customerToSaveRequest.taxCode())
                .address(customerToSaveRequest.address())
                .badPayer(false)
                .build();

        customer = customerRepository.save(customer);
        return customer.getId();


    }

    public Customer deleteCustomer (Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(()->new CustomerNotFound(id));
        customerRepository.delete(customer);
        return customer;
    }

    public Customer patchCustomer(Long id, CustomerToPatchRequest patchReq) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFound(id));

        if(patchReq.firstName()!=null) customer.setFirstName(patchReq.firstName());
        if(patchReq.lastName()!=null) customer.setLastName(patchReq.lastName());
        if(patchReq.dateOfBirth()!=null) customer.setDateOfBirth(patchReq.dateOfBirth());
        if(patchReq.taxCode()!=null) customer.setTaxCode(patchReq.taxCode());
        if(patchReq.email()!=null) customer.setEmail(patchReq.email());
        if(patchReq.phoneNumber()!=null) customer.setPhoneNumber(patchReq.phoneNumber());
        if(patchReq.badPayer()!=null) customer.setBadPayer(patchReq.badPayer());
        return customerRepository.save(customer);
    }
}
