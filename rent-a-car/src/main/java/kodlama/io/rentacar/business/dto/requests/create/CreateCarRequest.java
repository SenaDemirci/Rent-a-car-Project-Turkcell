package kodlama.io.rentacar.business.dto.requests.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCarRequest {
    private int modelId; //"car" tablosunda oto olarak model_id olarak çevirerek tutar. Tablodaki isimleri aynı verilmeli.
    private String plate;
}
