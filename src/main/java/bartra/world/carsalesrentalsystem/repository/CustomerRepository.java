package bartra.world.carsalesrentalsystem.repository;

import bartra.world.carsalesrentalsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CustomerRepository extends JpaRepository<Customer, Long>{
}
