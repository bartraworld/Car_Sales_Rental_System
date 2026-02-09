package bartra.world.carsalesrentalsystem.model;

public record BaseModel<T>(
        String status,
        String message,
        T data
) {
}
