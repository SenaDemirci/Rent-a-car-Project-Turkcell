package kodlama.io.rentacar.business.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalResponse {
    private int id;
    private int carId;
    private double dailyPrice;
    private int rentedForDays;
    private LocalDateTime startDate;
    private double totalPrice;

}

