package com.kodlamaio.invoiceservice.business.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceResponse {
    private String id;
    private String plate;
    private String cardHolder;
    private String modelName;
    private String brandName;
    private int modelYear;
    private double dailyPrice;
    private int rentedForDays;
    private double totalPrice;
    private LocalDateTime rentedAte;


}

