package kodlama.io.ecommerce.repository;

import kodlama.io.ecommerce.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    boolean existsByCardNumber(String cardNumber);
    Payment findByCardNumber(String cardNumber);
    boolean existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
            String cardNumber,String cardHolder,int cardExpirationYear,int cardExpirationMonth,String cardCvv
    );
}
