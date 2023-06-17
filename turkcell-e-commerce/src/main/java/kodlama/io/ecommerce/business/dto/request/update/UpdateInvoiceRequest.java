package kodlama.io.ecommerce.business.dto.request.update;

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
public class UpdateInvoiceRequest {
    @NotBlank
    private String cardHolder;
    @NotBlank
    private String productDescription;
    private double totalPrice;
    private LocalDateTime saleDate;

}
