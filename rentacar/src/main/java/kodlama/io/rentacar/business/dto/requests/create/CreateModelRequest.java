package kodlama.io.rentacar.business.dto.requests.create;

import jakarta.validation.constraints.NotNull;
import kodlama.io.rentacar.entities.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateModelRequest {
    private int brandId;
    private String name;

}
