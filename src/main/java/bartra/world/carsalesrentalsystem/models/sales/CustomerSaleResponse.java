package bartra.world.carsalesrentalsystem.models.sales;

import bartra.world.carsalesrentalsystem.models.cars.CarResponse;

public record CustomerSaleResponse(
        Long id,
        CarResponse car,
        String saleDate,
        Double salePrice
) {
}
