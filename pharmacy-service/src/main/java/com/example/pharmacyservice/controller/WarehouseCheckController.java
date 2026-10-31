package com.example.pharmacyservice.controller;

import com.example.pharmacyservice.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/pharmacy")
@RequiredArgsConstructor
public class WarehouseCheckController {

    private final WarehouseService warehouseService;

    /**
     * API kiểm tra hàng tồn kho: GET /api/v1/pharmacy/check-stock/{medicineId}
     */
    @GetMapping("/check-stock/{medicineId}")
    public Map<String, Object> checkMedicineStock(@PathVariable String medicineId) {
        long startTime = System.currentTimeMillis();
        String result = warehouseService.checkStock(medicineId);
        long executionTimeMs = System.currentTimeMillis() - startTime;

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("medicineId", medicineId);
        response.put("executionTimeMs", executionTimeMs + " ms");
        response.put("message", result);
        return response;
    }
}
