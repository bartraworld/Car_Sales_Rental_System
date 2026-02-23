package bartra.world.carsalesrentalsystem.models.cars;

import jakarta.validation.constraints.Min;

public record CarToUpdateRequest(
        String make,
        String model,
        @Min(1886)
        int year,
        double currentPrice,
        String plate)  {


}
