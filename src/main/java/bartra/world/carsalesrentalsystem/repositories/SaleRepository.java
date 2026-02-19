package bartra.world.carsalesrentalsystem.repositories;

import bartra.world.carsalesrentalsystem.entities.Customer;
import bartra.world.carsalesrentalsystem.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findSalesByCustomer(Customer customer);
}
