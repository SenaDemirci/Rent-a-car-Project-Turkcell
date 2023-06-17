package com.kodlamaio.rentalservice.api.clients;

import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import com.kodlamaio.commonpackage.utils.dto.CreateRentalPaymentRequest;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name="payment-service",fallback = PaymentClientFallBack.class)
public interface PaymentClient {
    @PostMapping(value = "/api/payments/check-payment-completed")
    ClientResponse checkIfPaymentCompleted(@RequestBody CreateRentalPaymentRequest request);
}
