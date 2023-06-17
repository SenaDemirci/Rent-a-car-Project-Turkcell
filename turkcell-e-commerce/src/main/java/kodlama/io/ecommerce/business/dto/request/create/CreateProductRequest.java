package kodlama.io.ecommerce.business.dto.request.create;

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
public class CreateProductRequest {
    private int categoryId;
    @NotBlank
    private String name;
    @Min(value = 1)
    private int quantity;
    @Min(value = 1)
    private double unitPrice;
    @NotBlank
    @Size(min = 10,max = 50)
    private String description;


}
