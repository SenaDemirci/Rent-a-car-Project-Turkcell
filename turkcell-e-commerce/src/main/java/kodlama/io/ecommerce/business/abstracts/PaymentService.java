package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.CreatePaymentRequest;
import kodlama.io.ecommerce.common.dto.CreateSalePaymentRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdatePaymentRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreatePaymentResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllPaymentsResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetPaymentResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdatePaymentResponse;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(int id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(int id, UpdatePaymentRequest request);
    void delete(int id);
    void processSalePayment(CreateSalePaymentRequest request);
}
