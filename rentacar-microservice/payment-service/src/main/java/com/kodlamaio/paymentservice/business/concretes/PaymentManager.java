package com.kodlamaio.paymentservice.business.concretes;


import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import com.kodlamaio.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.commonpackage.utils.kafka.producer.KafkaProducer;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import com.kodlamaio.paymentservice.business.abstracts.PosService;
import com.kodlamaio.paymentservice.business.dto.requests.CreatePaymentRequest;
import com.kodlamaio.paymentservice.business.dto.requests.UpdatePaymentRequest;
import com.kodlamaio.paymentservice.business.dto.responses.CreatePaymentResponse;
import com.kodlamaio.paymentservice.business.dto.responses.GetAllPaymentsResponse;
import com.kodlamaio.paymentservice.business.dto.responses.GetPaymentResponse;
import com.kodlamaio.paymentservice.business.dto.responses.UpdatePaymentResponse;
import com.kodlamaio.paymentservice.business.rules.PaymentBusinessRules;
import com.kodlamaio.paymentservice.entity.Payment;
import com.kodlamaio.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapperService mapper;
    private final PosService posService;
    private final PaymentBusinessRules rules;


    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> list=repository.findAll();
        List<GetAllPaymentsResponse> responses=list.stream().
                map(payment -> mapper.forResponse().map(payment,GetAllPaymentsResponse.class)).collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetPaymentResponse getById(UUID id) {
        rules.checkIfPaymentExists(id);
        Payment payment=repository.findById(id).orElseThrow();
        GetPaymentResponse response=mapper.forResponse().map(payment,GetPaymentResponse.class);

        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        rules.checkIfCardExists(request);
        Payment payment=mapper.forRequest().map(request,Payment.class);
        payment.setId(null);
        repository.save(payment);
        CreatePaymentResponse response=mapper.forResponse().map(payment, CreatePaymentResponse.class);

        return response;
    }


    @Override
    public UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request) {
        rules.checkIfPaymentExists(id);
        Payment payment=mapper.forRequest().map(request,Payment.class);
        payment.setId(id);
        repository.save(payment);
        UpdatePaymentResponse response=mapper.forResponse().map(payment,UpdatePaymentResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfPaymentExists(id);
        repository.deleteById(id);
    }



    @Override
    public ClientResponse checkIfPaymentCompleted(CreateRentalPaymentRequest request) {
        var response=new ClientResponse();
        validatePaymentCompleted(request, response);

        return response;
    }

    private void validatePaymentCompleted(CreateRentalPaymentRequest request, ClientResponse response) {
        try {
            rules.checkIfPaymentIsValid(request);
            Payment payment=repository.findByCardNumber(request.getCardNumber());
            rules.checkIfBalanceIsEnough(request.getPrice(),payment.getBalance());
            posService.pay(); // fake pos service
            payment.setBalance(payment.getBalance()-request.getPrice());
            repository.save(payment);// payment update
            response.setSuccess(true);
        }catch (BusinessException exception){
            response.setSuccess(false);
            response.setMessage(exception.getMessage());
        }
    }

}
