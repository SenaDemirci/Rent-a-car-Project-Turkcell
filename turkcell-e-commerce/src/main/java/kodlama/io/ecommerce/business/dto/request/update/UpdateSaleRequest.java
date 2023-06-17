package kodlama.io.ecommerce.business.dto.request.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class UpdateSaleRequest {
    @NotNull
    private List<Integer> productIds;
    @NotNull
    private LocalDateTime createdAt;

}
