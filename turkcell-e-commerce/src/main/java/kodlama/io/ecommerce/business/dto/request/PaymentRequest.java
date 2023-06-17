package kodlama.io.ecommerce.business.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    @NotBlank
    private String cardNumber;
    @NotBlank
    private String cardHolder;
    @Min(2023)
    private int cardExpirationYear;
    @Min(value = 1)
    @Max(value = 12)
    private int cardExpirationMonth;
    @Size(max = 3,min = 3)
    private String cardCvv;
}
