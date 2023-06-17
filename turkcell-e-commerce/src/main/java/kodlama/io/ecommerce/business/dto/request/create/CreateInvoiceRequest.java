package kodlama.io.ecommerce.business.dto.request.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {
    @NotBlank
    private String cardHolder;
    @NotBlank
    private String productDescription;
    @Min(0)
    private double totalPrice;
    private LocalDateTime saleDate;

}
