package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.car.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.get.car.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import kodlama.io.rentacar.business.rules.CarBusinessRules;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapper mapper;
    private final CarBusinessRules rules;
    @Override
    public List<GetAllCarsResponse> getAll(boolean includeMaintenance) {
            List<Car> cars=filterCarsByMaintenanceState(includeMaintenance);
            List<GetAllCarsResponse> responses=cars.stream().
                    map(car -> mapper.map(car, GetAllCarsResponse.class)).collect(Collectors.toList());

            return responses;
    }
    @Override
    public GetCarResponse getById(int id) {
        rules.checkIfCarExists(id);
        Car car=repository.findById(id).orElseThrow();
        GetCarResponse response=mapper.map(car,GetCarResponse.class);

        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        rules.checkIfCarExistsByPlate(request.getPlate());
        Car car=mapper.map(request,Car.class);
        car.setId(0);
        car.setState(State.AVAILABLE);
        Car createdCar=repository.save(car);
        CreateCarResponse response=mapper.map(createdCar,CreateCarResponse.class);

        return response;
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        rules.checkIfCarExists(id);
        Car car=mapper.map(request,Car.class);
        car.setId(id);
        Car createdCar= repository.save(car);
        UpdateCarResponse response=mapper.map(createdCar,UpdateCarResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfCarExists(id);
        repository.deleteById(id);
    }

    @Override
    public void changeState(int carId, State state) {
       Car car= repository.findById(carId).orElseThrow();
       car.setState(state);
       repository.save(car);// state durumunu güncelledik
    }


    List<Car>  filterCarsByMaintenanceState(boolean includeMaintenance){
        if(includeMaintenance){
            return repository.findAll();
        }
         return repository.findAllByStateIsNot(State.MAINTENANCE);
    }
}