package bartra.world.carsalesrentalsystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    // si deve usare @Column per specificare il nome della colonna nel database se è diverso dal nome del campo
    // perché "year" è una parola riservata in SQL
    @Column(name = "production_year")
    @Min(1886) // La prima auto è stata prodotta nel 1886
    private int year;
    private double currentPrice;
}
