package bartra.world.carsalesrentalsystem.repository;

import bartra.world.carsalesrentalsystem.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
