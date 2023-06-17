package kodlama.io.rentacar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String plate;
    private String cardHolder;
    private String modelName;
    private String brandName;
    private int modelYear;
    private double dailyPrice;
    private int rentedForDays;
    private double totalPrice;
    private LocalDateTime rentedAte;

}
