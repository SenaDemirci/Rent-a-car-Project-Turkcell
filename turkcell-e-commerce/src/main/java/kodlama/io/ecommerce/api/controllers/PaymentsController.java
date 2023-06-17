package kodlama.io.ecommerce.api.controllers;

import jakarta.validation.Valid;
import kodlama.io.ecommerce.business.abstracts.PaymentService;
import kodlama.io.ecommerce.business.dto.request.create.CreatePaymentRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdatePaymentRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreatePaymentResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllPaymentsResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetPaymentResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdatePaymentResponse;
import kodlama.io.ecommerce.entities.Payment;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentService service;

    @GetMapping
    public List<GetAllPaymentsResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable int id){
        return service.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest request){
        return service.add(request);
    }
    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@PathVariable int id, @Valid @RequestBody UpdatePaymentRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
