package bartra.world.carsalesrentalsystem.models.customers;

import java.time.LocalDate;

public record CustomerToPatchRequest(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String phoneNumber,
        String email,
        String taxCode,
        String address,
        Boolean badPayer) {
}
