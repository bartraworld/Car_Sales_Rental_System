package bartra.world.carsalesrentalsystem.model.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;


public record CustomerResponse(

         Long id,
         @JsonProperty("first_name")
         String firstName,
         @JsonProperty("last_name")
         String lastName,
         @JsonProperty("date_of_birth")
         LocalDate dateOfBirth,
         @JsonProperty("phone_number")
         String phoneNumber,
         String email,
         @JsonProperty("tax_code")
         String taxCode,
         String address,
         @JsonProperty("bad_payer")
         Boolean badPayer

) {
}
