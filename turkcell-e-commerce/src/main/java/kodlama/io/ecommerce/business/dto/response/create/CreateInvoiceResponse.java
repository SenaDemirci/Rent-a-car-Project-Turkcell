package kodlama.io.ecommerce.business.dto.response.create;

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
public class CreateInvoiceResponse {
    private int id;
    private String cardHolder;
   private String productDescription;
    private double totalPrice;
    private LocalDateTime saleDate;
}
