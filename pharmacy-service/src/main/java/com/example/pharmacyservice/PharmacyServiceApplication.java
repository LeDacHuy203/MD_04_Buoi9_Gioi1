package com.example.pharmacyservice;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class PharmacyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmacyServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(Pharmacy test) {
        return args -> {
            System.out.println("==================================================");
            System.out.println(">>> KHỞI ĐỘNG PHARMACY-SERVICE THÀNH CÔNG! <<<");
            System.out.println(">>> Tên chi nhánh : " + test.getBranchName());
            System.out.println(">>> Hotline hỗ trợ: " + test.getHotline());
            System.out.println(">>> Datasource URL: " + test.getDatasourceUrl());
            System.out.println("==================================================");
        };
    }
}
