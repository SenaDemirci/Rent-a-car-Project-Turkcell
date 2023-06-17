package kodlama.io.ecommerce.business.rules;

import kodlama.io.ecommerce.business.dto.request.create.CreatePaymentRequest;
import kodlama.io.ecommerce.common.constants.Messages;
import kodlama.io.ecommerce.common.dto.CreateSalePaymentRequest;
import kodlama.io.ecommerce.core.exceptions.BusinessException;
import kodlama.io.ecommerce.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentBusinessRules {
    private final PaymentRepository repository;

    public void checkIfExistsById(int id){
        if(!repository.existsById(id)){
            throw new BusinessException(Messages.Payment.NotExists);
        }
    }
    public void checkIfCardExistsByNumber(String cardNumber){
        if(repository.existsByCardNumber(cardNumber)){
            throw new BusinessException(Messages.Payment.CardNumberAlreadyExists);
        }

    }
    public void checkIfPaymentIsValid(CreateSalePaymentRequest request){
        if(!repository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getCardHolder(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv())){
            throw new BusinessException(Messages.Payment.NotAValidPayment);
        }
    }

    public void checkIfBalanceIsEnough(double balance,double price){
        if(balance<price){
            throw new RuntimeException(Messages.Payment.NotEnoughMoney);
        }
    }

}
