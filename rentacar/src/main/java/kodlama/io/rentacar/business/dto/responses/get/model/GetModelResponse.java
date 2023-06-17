package kodlama.io.rentacar.business.dto.responses.get.model;

import kodlama.io.rentacar.entities.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetModelResponse {
    private int id;
    private String name;
    private int brandId;

}
