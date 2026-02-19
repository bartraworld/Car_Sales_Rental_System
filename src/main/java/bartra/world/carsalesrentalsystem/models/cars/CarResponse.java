package bartra.world.carsalesrentalsystem.models.cars;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CarResponse(
        Long id,
        String make,
        String model,
        @JsonProperty("production_year")
        Integer productionYear,
        @JsonProperty("current_price")
        Double currentPrice
) {
}
