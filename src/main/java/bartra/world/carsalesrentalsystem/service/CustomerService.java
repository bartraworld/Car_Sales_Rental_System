package bartra.world.carsalesrentalsystem.service;


import bartra.world.carsalesrentalsystem.entity.Customer;
import bartra.world.carsalesrentalsystem.model.IdModel;

import bartra.world.carsalesrentalsystem.model.customer.CustomerResponse;
import bartra.world.carsalesrentalsystem.model.customer.CustomerToSaveRequest;
import bartra.world.carsalesrentalsystem.repository.CustomerRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class CustomerService {

    final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).get();

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getDateOfBirth(),
                customer.getPhoneNumber(),
                customer.getEmail(),
                customer.getTaxCode(),
                customer.getAddress(),
                customer.getBadPayer()

                );
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(customer -> new CustomerResponse(

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
        ).toList();
    }

    public IdModel addCustomer(CustomerToSaveRequest customerToSaveRequest) {
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
        return new IdModel(customer.getId());


    }
}
