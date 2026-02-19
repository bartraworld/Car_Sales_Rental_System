package bartra.world.carsalesrentalsystem.models.sales;

import java.time.LocalDate;

public record SaleToCreate(
        Long carId,
        Long customerId,
        LocalDate saleDate,
        Double salePrice
) {
}
