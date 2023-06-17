package kodlama.io.rentacar.business.dto.requests.create;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBrandReguest {
    @NotBlank
    @Length(min=2,message = "length must be greater than 2")
    private String name;
}
