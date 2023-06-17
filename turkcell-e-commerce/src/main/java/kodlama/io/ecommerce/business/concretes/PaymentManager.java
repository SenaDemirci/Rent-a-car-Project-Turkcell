package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.PaymentService;
import kodlama.io.ecommerce.business.abstracts.PosService;
import kodlama.io.ecommerce.business.dto.request.create.CreatePaymentRequest;
import kodlama.io.ecommerce.common.dto.CreateSalePaymentRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdatePaymentRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreatePaymentResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllPaymentsResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetPaymentResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdatePaymentResponse;
import kodlama.io.ecommerce.business.rules.PaymentBusinessRules;
import kodlama.io.ecommerce.entities.Payment;
import kodlama.io.ecommerce.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapper mapper;
    private final PaymentBusinessRules rules;
    private final PosService posService;
    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> payments=repository.findAll();
        List<GetAllPaymentsResponse> responses=payments
                .stream()
                .map(payment -> mapper.map(payment, GetAllPaymentsResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetPaymentResponse getById(int id) {
        rules.checkIfExistsById(id);
        Payment payment=repository.findById(id).orElseThrow();
        GetPaymentResponse response=mapper.map(payment,GetPaymentResponse.class);

        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        rules.checkIfCardExistsByNumber(request.getCardNumber());
        Payment payment=mapper.map(request,Payment.class);
        payment.setId(0);
        repository.save(payment);
        CreatePaymentResponse response=mapper.map(payment,CreatePaymentResponse.class);

        return response;
    }

    @Override
    public UpdatePaymentResponse update(int id, UpdatePaymentRequest request) {
        rules.checkIfExistsById(id);
        Payment payment=mapper.map(request,Payment.class);
        payment.setId(id);
        repository.save(payment);
        UpdatePaymentResponse response=mapper.map(payment,UpdatePaymentResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfExistsById(id);
        repository.deleteById(id);
    }

    @Override
    public void processSalePayment(CreateSalePaymentRequest request) {
        rules.checkIfPaymentIsValid(request);
        Payment payment=repository.findByCardNumber(request.getCardNumber());
        rules.checkIfBalanceIsEnough(payment.getBalance(),request.getPrice());
        posService.pay();
        payment.setBalance(payment.getBalance()- request.getPrice());
        repository.save(payment);
    }

}
