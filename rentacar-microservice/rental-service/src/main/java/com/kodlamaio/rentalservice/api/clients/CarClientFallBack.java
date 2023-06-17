package com.kodlamaio.rentalservice.api.clients;

import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import com.kodlamaio.commonpackage.utils.dto.GetCarResponse;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class CarClientFallBack implements CarClient{

    @Override
    public ClientResponse checkIfCarAvailable(UUID carId) {
        log.info("INVENTORY SERVICE IS DOWN!");
        throw  new RuntimeException("INVENTORY SERVICE IS DOWN");
    }

    @Override
    public GetCarResponse getRentalCarById(UUID carId) {
        log.info("INVENTORY SERVICE IS DOWN!");
        throw  new RuntimeException("INVENTORY SERVICE IS DOWN");
    }
}
