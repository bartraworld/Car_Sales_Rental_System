package bartra.world.carsalesrentalsystem.models.cars;

import jakarta.validation.constraints.Min;

public record CarToPatchRequest(
        String make,
        String model,
        @Min(1886)
        Integer year,
        Double currentPrice,
        String plate) {


}
