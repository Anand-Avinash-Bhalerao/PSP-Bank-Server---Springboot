package com.billion_dollor_company.Bank_Server;

import com.billion_dollor_company.Bank_Server.util.cryptography.EncryptionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankServerApplication.class, args);
    }

}
