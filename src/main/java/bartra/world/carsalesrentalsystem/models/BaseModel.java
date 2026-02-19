package bartra.world.carsalesrentalsystem.models;

public record BaseModel<T>(
        String status,
        String message,
        T data
) {
}
