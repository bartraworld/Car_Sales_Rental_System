package bartra.world.carsalesrentalsystem.model.car;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CarToSaveRequest(
        @NotBlank
        String make,
        @NotBlank
        String model,
        @JsonProperty("production_year")
        @Min(1886)
        int year,
        @JsonProperty("current_price")
        double currentPrice
) {}
