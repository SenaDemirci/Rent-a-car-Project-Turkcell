
package kodlama.io.rentacar.business.concretes;

        import kodlama.io.rentacar.business.abstracts.CarService;
        import kodlama.io.rentacar.business.abstracts.MaintenanceService;
        import kodlama.io.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
        import kodlama.io.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
        import kodlama.io.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
        import kodlama.io.rentacar.business.dto.responses.get.car.GetCarResponse;
        import kodlama.io.rentacar.business.dto.responses.get.maintenance.GetAllMaintenancesResponse;
        import kodlama.io.rentacar.business.dto.responses.get.maintenance.GetMaintenanceResponse;
        import kodlama.io.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
        import kodlama.io.rentacar.business.rules.MaintenanceBusinessRules;
        import kodlama.io.rentacar.entities.Car;
        import kodlama.io.rentacar.entities.Maintenance;
        import kodlama.io.rentacar.entities.enums.State;
        import kodlama.io.rentacar.repository.MaintenanceRepository;
        import lombok.AllArgsConstructor;
        import org.modelmapper.ModelMapper;
        import org.springframework.stereotype.Service;

        import java.time.LocalDate;
        import java.time.LocalDateTime;
        import java.util.List;
        import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final CarService carService;
    private final ModelMapper mapper;
    private final MaintenanceBusinessRules rules;


    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> list=maintenanceRepository.findAll();
        List<GetAllMaintenancesResponse> responses=list.stream()
                .map(maintenance -> mapper.map(maintenance,GetAllMaintenancesResponse.class)).
                collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int car_id) {
        rules.checkIfCarIsNotUnderMaintenance(car_id);
        Maintenance maintenance=maintenanceRepository.findByCarIdAndIsCompletedIsFalse(car_id);
        maintenance.setCompleted(true);
        maintenance.setEndMaintenance(LocalDateTime.now());//bakımın bitiş tarihi set edildi.
        maintenanceRepository.save(maintenance);
        carService.changeState(car_id,State.AVAILABLE);//araba bakımdan cıktı
        GetMaintenanceResponse response=mapper.map(maintenance,GetMaintenanceResponse.class);

        return response;
    }


    @Override
    public GetMaintenanceResponse getById(int id) {
        rules.checkIfMaintenanceExistsById(id);
        Maintenance maintenance=maintenanceRepository.findById(id).orElseThrow();
        GetMaintenanceResponse response=mapper.map(maintenance,GetMaintenanceResponse.class);

        return response;
    }
    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        rules.checkIfCarUnderMaintenance(request.getCarId());
        rules.checkCarAvailabilityForMaintenance(carService.getById(request.getCarId()).getState());
        Maintenance maintenance=mapper.map(request,Maintenance.class);
        maintenance.setId(0);
        maintenance.setStartMaintenance(LocalDateTime.now());
        maintenance.setEndMaintenance(null);
        maintenance.setCompleted(false);
        maintenanceRepository.save(maintenance);
        carService.changeState(request.getCarId(), State.MAINTENANCE);
        CreateMaintenanceResponse response=mapper.map(maintenance,CreateMaintenanceResponse.class);

        return response;
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        rules.checkIfMaintenanceExistsById(id);
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);
        maintenanceRepository.save(maintenance);
        UpdateMaintenanceResponse response = mapper.map(maintenance, UpdateMaintenanceResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfMaintenanceExistsById(id);
        makeCarAvailableIfIsCompletedFalse(id);
        maintenanceRepository.deleteById(id);
    }

    private void makeCarAvailableIfIsCompletedFalse(int id){
        int car_id=maintenanceRepository.findById(id).get().getCar().getId();
        if(maintenanceRepository.existsByCarIdAndIsCompletedIsFalse(car_id)){
            carService.changeState(car_id, State.AVAILABLE);
        }
    }

}