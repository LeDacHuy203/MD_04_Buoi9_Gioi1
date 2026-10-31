package com.example.pharmacyservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pharmacy {

    @Value("${app.branch-name:Chưa có tên}")
    private String branchName;

    @Value("${app.hotline:Chưa có hotline}")
    private String hotline;

    @Value("${spring.datasource.url:Chưa cấu hình URL}")
    private String datasourceUrl;
}
