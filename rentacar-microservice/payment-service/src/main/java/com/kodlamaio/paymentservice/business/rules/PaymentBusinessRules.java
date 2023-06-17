package com.kodlamaio.paymentservice.business.rules;


import com.kodlamaio.commonpackage.utils.constants.Messages;
import com.kodlamaio.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.paymentservice.business.dto.requests.CreatePaymentRequest;
import com.kodlamaio.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentBusinessRules {
    private final PaymentRepository repository;

    public void checkIfPaymentExists(UUID id){
        if(!repository.existsById(id)){
            throw new BusinessException(Messages.Payment.NotFound);
        }
    }
    public void checkIfBalanceIsEnough(double price,double balance){
        if(balance<price){
            throw new BusinessException(Messages.Payment.NotEnoughMoney);
        }
    }
    public void checkIfCardExists(CreatePaymentRequest request) { // Kart numarasının unique kontrolü
        if(repository.existsByCardNumber(request.getCardNumber())){
            throw new BusinessException(Messages.Payment.CardNumberAlreadyExists);
        }
    }
    public void checkIfPaymentIsValid(CreateRentalPaymentRequest request) { //Geçerli Kart kontrolü
        if(!repository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getCardHolder(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv())){
            throw  new BusinessException(Messages.Payment.NotAValidPayment);
        }
    }
}
