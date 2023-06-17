package com.kodlamaio.rentalservice.business.concretes;

import com.kodlamaio.commonpackage.events.rental.RentalCreatedEvent;
import com.kodlamaio.commonpackage.events.rental.RentalDeletedEvent;
import com.kodlamaio.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.kodlamaio.commonpackage.utils.dto.GetCarResponse;
import com.kodlamaio.commonpackage.utils.kafka.producer.KafkaProducer;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.rentalservice.api.clients.CarClient;
import com.kodlamaio.rentalservice.business.abstracts.RentalService;
import com.kodlamaio.rentalservice.business.dto.requests.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.dto.requests.UpdateRentalRequest;
import com.kodlamaio.rentalservice.business.dto.responses.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.dto.responses.GetAllRentalsResponse;
import com.kodlamaio.rentalservice.business.dto.responses.GetRentalResponse;
import com.kodlamaio.rentalservice.business.dto.responses.UpdateRentalResponse;
import com.kodlamaio.rentalservice.business.rules.RentalBusinessRules;
import com.kodlamaio.rentalservice.entities.Rental;
import com.kodlamaio.rentalservice.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

    private final RentalRepository repository;
    private final ModelMapperService mapper;
    private final RentalBusinessRules rules;
    private final KafkaProducer producer;
    private final CarClient carClient;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        var rentals = repository.findAll();
        var response = rentals
                .stream()
                .map(rental -> mapper.forResponse().map(rental, GetAllRentalsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetRentalResponse getById(UUID id) {
        rules.checkIfRentalExists(id);
        var rental = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(rental, GetRentalResponse.class);

        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        rules.ensureCarIsAvailable(request.getCarId()); // car service control
        var rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(null);
        rental.setTotalPrice(getTotalPrice(rental));
        rental.setRentedAt(LocalDateTime.now());

        CreateRentalPaymentRequest rentalPaymentRequest=new CreateRentalPaymentRequest();
        mapper.forRequest().map(request.getPaymentRequest(),rentalPaymentRequest);
        rentalPaymentRequest.setPrice(getTotalPrice(rental));
        rules.paymentIsCompleted(rentalPaymentRequest);


        repository.save(rental);

        GetCarResponse getCarResponse = carClient.getRentalCarById(rental.getCarId());
        sendKafkaRentalCreatedEvent(getCarResponse,request, rental.getRentedAt());


        var response = mapper.forResponse().map(rental, CreateRentalResponse.class);

        return response;
    }



    @Override
    public UpdateRentalResponse update(UUID id, UpdateRentalRequest request) {
        rules.checkIfRentalExists(id);
        var rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(id);
        repository.save(rental);
        var response = mapper.forResponse().map(rental, UpdateRentalResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfRentalExists(id);
        sendKafkaRentalDeletedEvent(id);
        repository.deleteById(id);

    }
    private void sendKafkaRentalCreatedEvent(GetCarResponse getCarResponse, CreateRentalRequest request, LocalDateTime rentedAt) {
        RentalCreatedEvent event = new RentalCreatedEvent();
        event.setCarId(request.getCarId());
        event.setCardHolder(request.getPaymentRequest().getCardHolder());
        event.setTotalPrice(request.getDailyPrice() * request.getRentedForDays());
        event.setRentedAt(rentedAt);
        event.setPlate(getCarResponse.getPlate());
        event.setBrandName(getCarResponse.getModelBrandName());
        event.setModelName(getCarResponse.getModelName());
        event.setModelYear(getCarResponse.getModelYear());
        event.setDailyPrice(request.getDailyPrice());
        event.setRentedForDays(request.getRentedForDays());
        producer.sendMessage(event, "rental-created");
    }


    private void sendKafkaRentalDeletedEvent(UUID id) {
        var carId=repository.findById(id).orElseThrow().getCarId();
        producer.sendMessage(new RentalDeletedEvent(carId),"rental-deleted");
    }

    private double getTotalPrice(Rental rental){
        return rental.getDailyPrice()*rental.getRentedForDays();
    }
}
