package kodlama.io.rentacar.business.dto.requests.update;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInvoiceRequest {

    @NotBlank(message = "Alan boş geçilemez")
    private String cardHolder;
    @NotBlank(message = "Alan boş geçilemez")
    private String modelName;
    @NotBlank(message = "Alan boş geçilemez")
    private String brandName;
    @NotNull
    private String plate;
    @Min(value = 1996)
    private int modelYear;
    @Min(0)
    private double dailyPrice;
    @Min(0)
    private int rentedForDays;
    private LocalDateTime rentedAte;

}
