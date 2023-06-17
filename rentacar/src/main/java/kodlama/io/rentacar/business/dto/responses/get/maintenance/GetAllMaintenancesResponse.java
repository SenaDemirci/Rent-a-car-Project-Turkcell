package kodlama.io.rentacar.business.dto.responses.get.maintenance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMaintenancesResponse {
    private int id;
    private String information;
    private LocalDate startMaintenance;
    private LocalDate endMaintenance;
    private boolean isCompleted;
    private int carId;
}
