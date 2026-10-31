package com.example.pharmacyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillResponse {
    private double medicineAmount;
    private double vatRate;
    private double vatAmount;
    private double totalAmount;
}
