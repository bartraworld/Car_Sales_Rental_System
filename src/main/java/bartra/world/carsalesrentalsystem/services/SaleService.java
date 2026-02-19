package bartra.world.carsalesrentalsystem.services;

import bartra.world.carsalesrentalsystem.entities.Car;
import bartra.world.carsalesrentalsystem.entities.Customer;
import bartra.world.carsalesrentalsystem.entities.Sale;
import bartra.world.carsalesrentalsystem.repositories.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService {
    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Long createSale(Car car, Customer customer, Double salePrice, LocalDate saleDate) {
        var sale = Sale.builder()
                .carSold(car)
                .customer(customer)
                .salePrice(salePrice)
                .saleDate(saleDate)
                .build();
        var saleSaved = saleRepository.save(sale);
        return saleSaved.getId();
    }


    public List<Sale> getSalesByCustomer(Customer customer) {
        return saleRepository.findSalesByCustomer(customer);
    }
}
