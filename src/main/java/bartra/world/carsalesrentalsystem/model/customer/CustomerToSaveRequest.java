package bartra.world.carsalesrentalsystem.model.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record CustomerToSaveRequest(

        @NotBlank
        @JsonProperty("first_name")
        String firstName,

        @NotBlank
        @JsonProperty("last_name")
        String lastName,

        @Past
        @JsonProperty("date_of_birth")
        LocalDate dateOfBirth,

        @NotBlank
        @JsonProperty("phone_number")
        String phoneNumber,

        @NotBlank
        String email,

        @NotBlank
        @JsonProperty("tax_code")
        String taxCode,

        @NotBlank
        String address


) {
}
