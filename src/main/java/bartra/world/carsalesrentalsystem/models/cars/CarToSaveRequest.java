package bartra.world.carsalesrentalsystem.models.cars;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CarToSaveRequest(
        @NotBlank
        String make,
        @NotBlank
        String model,
        @JsonProperty("production_year")
        @Min(value = 1886, message = "Invalid production year")
        int year,
        @JsonProperty("current_price")
        double currentPrice,
        @NotBlank
        @Pattern(
                regexp = "^[A-Z]{2}[0-9]{3}[A-Z]{2}$",
                message = "Invalid plate format. Expected format: 2 uppercase letters, followed by 3 digits, followed by 2 uppercase letters (e.g., AB123CD)"
        )
        String plate
) {
}
