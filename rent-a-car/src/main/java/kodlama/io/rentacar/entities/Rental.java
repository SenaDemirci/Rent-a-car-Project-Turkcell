package kodlama.io.rentacar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double dailyPrice;
    private  int rentedForDays;
    private double totalPrice;
    private LocalDateTime startDate;

    //rental tablosu içinde carId foreign key olarak olmalı
    //ilişki sahibi(rental) many yapılır.
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
