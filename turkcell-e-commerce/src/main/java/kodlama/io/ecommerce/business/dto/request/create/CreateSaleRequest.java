package kodlama.io.ecommerce.business.dto.request.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kodlama.io.ecommerce.business.dto.request.PaymentRequest;
import kodlama.io.ecommerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSaleRequest {

    private List<Integer> productIds;
    private PaymentRequest paymentRequest;
}
