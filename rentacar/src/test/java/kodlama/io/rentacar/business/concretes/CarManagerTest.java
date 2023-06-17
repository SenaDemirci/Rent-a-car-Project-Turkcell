package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.responses.get.car.GetCarResponse;
import kodlama.io.rentacar.business.rules.CarBusinessRules;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CarManagerTest {
    private CarRepository repository;
    private ModelMapper mapper;
    private CarBusinessRules rules;
    private CarService service;
    @BeforeEach
    public void setUp(){
        repository= Mockito.mock(CarRepository.class);
        mapper=Mockito.mock(ModelMapper.class);
        rules=Mockito.mock(CarBusinessRules.class);
        service=new CarManager(repository,mapper,rules);
    }

    @Test
    public void testGetById_withId_thenResponse() throws  Exception{
        int id=1;
        Car car=new Car();
        car.setId(1);
        car.setPlate("25 EA 125");
        car.setModelYear(2017);
        car.setState(State.AVAILABLE);
        car.setDailyPrice(1000);

        GetCarResponse response=new GetCarResponse();
        response.setId(car.getId());
        response.setModelId(1);
        response.setModelYear(car.getModelYear());
        response.setPlate(car.getPlate());
        response.setState(car.getState());
        response.setDailyPrice(car.getDailyPrice());



        when(repository.findById(id)).thenReturn(Optional.of(car));
        doNothing().when(rules).checkIfCarExists(id);
        when(mapper.map(car,GetCarResponse.class)).thenReturn(response);

        GetCarResponse getCarResponse=service.getById(id);
        assertEquals(response,getCarResponse);

    }

}