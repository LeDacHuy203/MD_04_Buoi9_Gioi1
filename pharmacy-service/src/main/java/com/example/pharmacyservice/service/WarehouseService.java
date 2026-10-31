package com.example.pharmacyservice.service;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final RestTemplate restTemplate;

    /**
     * Gọi API kiểm tra hàng tồn sang dịch vụ kho tổng warehouse-service (Port 8082).
     * Áp dụng Circuit Breaker với tên "warehouseCB" theo đúng yêu cầu đề bài.
     */
    @CircuitBreaker(name = "warehouseCB", fallbackMethod = "checkStockFallback")
    public String checkStock(String medicineId) {
        System.out.println(">>> [WAREHOUSE CALL] Đang kết nối tới kho tổng kiểm tra thuốc: " + medicineId);
        String warehouseUrl = "http://localhost:8082/api/v1/warehouse/stock/" + medicineId;
        return restTemplate.getForObject(warehouseUrl, String.class);
    }

    /**
     * Hàm Fallback: Tự động kích hoạt khi gọi kho tổng bị lỗi hoặc khi mạch chuyển sang OPEN.
     */
    public String checkStockFallback(String medicineId, Throwable t) {
        if (t instanceof CallNotPermittedException) {
            System.err.println(">>> [CIRCUIT OPEN - FAIL-FAST] Mạch đang OPEN! Từ chối gọi kho, trả về lỗi ngay lập tức!");
            return ">>> [FAIL-FAST - MẠCH ĐANG NGẮT (OPEN)]: Kho tổng hiện đang sập hoặc phản hồi quá chậm. Hệ thống đã tự động ngắt mạch trong 20s để bảo vệ máy tính tiền.";
        }
        System.err.println(">>> [CALL FAILED]: Gọi kho tổng thất bại (" + t.getClass().getSimpleName() + "). Đang tích lũy lỗi...");
        return ">>> [FALLBACK]: Không thể kết nối tới kho tổng cho thuốc [" + medicineId + "]. Tạm thời sử dụng dữ liệu tồn kho cục bộ.";
    }
}
