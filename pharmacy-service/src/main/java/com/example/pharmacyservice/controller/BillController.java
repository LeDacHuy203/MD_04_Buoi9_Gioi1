package com.example.pharmacyservice.controller;

import com.example.pharmacyservice.dto.BillRequest;
import com.example.pharmacyservice.dto.BillResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bill")
@RefreshScope // BẮT BUỘC: Cho phép làm mới cấu hình trong bộ nhớ khi gọi /actuator/refresh
public class BillController {

    @Value("${pharmacy.vat-rate:0.08}")
    private double vatRate;

    @PostMapping
    public BillResponse calculateBill(@RequestBody BillRequest request) {
        double medicineAmount = request.getAmount();
        double vatAmount = medicineAmount * vatRate;
        double totalAmount = medicineAmount + vatAmount;

        return new BillResponse(medicineAmount, vatRate, vatAmount, totalAmount);
    }
}
