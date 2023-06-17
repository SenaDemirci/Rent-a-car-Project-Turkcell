package kodlama.io.ecommerce.business.dto.response.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSaleResponse {
    private int id;
    private List<Integer> productIds;
    private double totalPrice;
    private LocalDateTime createdAt;

}
