package com.billion_dollor_company.Bank_Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.billion_dollor_company.Bank_Server.models"})
public class BankServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankServerApplication.class, args);
    }

}
