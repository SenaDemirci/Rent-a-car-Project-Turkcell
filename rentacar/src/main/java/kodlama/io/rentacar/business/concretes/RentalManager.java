package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.*;
import kodlama.io.rentacar.business.dto.requests.create.CreateInvoiceRequest;
import kodlama.io.rentacar.business.dto.requests.create.CreateRentalRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateRentalRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateRentalResponse;

import kodlama.io.rentacar.business.dto.responses.get.car.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.rental.GetAllRentalsResponse;
import kodlama.io.rentacar.business.dto.responses.get.rental.GetRentalResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateRentalResponse;
import kodlama.io.rentacar.business.rules.RentalBusinessRules;

import kodlama.io.rentacar.common.dto.CreateRentalPaymentRequest;
import kodlama.io.rentacar.entities.Rental;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;
    private final RentalBusinessRules rules;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> list=repository.findAll();
        List<GetAllRentalsResponse> responses=list.
                stream().map(rental -> mapper.map(rental, GetAllRentalsResponse.class)).collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetRentalResponse getById(int id) {
        rules.checkIfRentalExists(id);
        Rental rental=repository.findById(id).orElseThrow();
        GetRentalResponse response=mapper.map(rental,GetRentalResponse.class);

        return response;

    }


    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        rules.checkIfCarAvailable(carService.getById(request.getCarId()).getState());
        Rental rental=mapper.map(request ,Rental.class);
        rental.setId(0);
        rental.setStartDate(LocalDateTime.now());
        rental.setTotalPrice(getTotalPrice(rental));
        //PAYMENT CREATED
        CreateRentalPaymentRequest rentalPaymentRequest=new CreateRentalPaymentRequest();
        mapper.map(request.getPaymentRequest(),rentalPaymentRequest);
        rentalPaymentRequest.setPrice(getTotalPrice(rental));
        paymentService.processRentalPayment(rentalPaymentRequest);
        // SUCCESS RENTAL
        Rental createdRental=repository.save(rental);
        carService.changeState(request.getCarId(), State.RENTED);
        CreateRentalResponse response=mapper.map(createdRental,CreateRentalResponse.class);
        //INVOİCE CREATED
        CreateInvoiceRequest invoiceRequest=new CreateInvoiceRequest();
        createInvoiceRequest(request,invoiceRequest,rental);
        invoiceService.add(invoiceRequest);

        return response;
    }



    @Override
    public UpdateRentalResponse update(int id, UpdateRentalRequest request) {
        rules.checkIfRentalExists(id);
        Rental rental=mapper.map(request ,Rental.class);
        rental.setId(id);
        rental.setTotalPrice(getTotalPrice(rental));
        Rental updatedRental=repository.save(rental);
        UpdateRentalResponse response=mapper.map(updatedRental,UpdateRentalResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfRentalExists(id);
        int carId=repository.findById(id).get().getCar().getId();
        carService.changeState(carId, State.AVAILABLE);
        repository.deleteById(id);
    }


    private double getTotalPrice(Rental rental){
        return rental.getDailyPrice()*rental.getRentedForDays();
    }
    private void createInvoiceRequest(CreateRentalRequest request, CreateInvoiceRequest invoiceRequest,Rental rental ) {
        GetCarResponse car=carService.getById(request.getCarId());

        invoiceRequest.setModelName(car.getModelName());
        invoiceRequest.setRentedAte(rental.getStartDate());
        invoiceRequest.setBrandName(car.getModelBrandName());
        invoiceRequest.setCardHolder(request.getPaymentRequest().getCardHolder());
        invoiceRequest.setPlate(car.getPlate());
        invoiceRequest.setModelYear(car.getModelYear());
        invoiceRequest.setRentedForDays(request.getRentedForDays());
        invoiceRequest.setDailyPrice(request.getDailyPrice());

    }
}
