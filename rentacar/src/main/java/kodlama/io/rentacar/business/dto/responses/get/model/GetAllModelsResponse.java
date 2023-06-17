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
public class GetAllModelsResponse {
    private int id;
    private int  brandId;
     private String name;


}
