package bartra.world.carsalesrentalsystem.models.cars;

public record CarToUpdateRequest(
        String make,
        String model,
        int year,
        double currentPrice,
        String plate)  {


}
