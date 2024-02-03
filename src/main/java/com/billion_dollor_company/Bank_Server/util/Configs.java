package com.billion_dollor_company.Bank_Server.util;

import com.billion_dollor_company.Bank_Server.repository.TransactionInfoRepository;
import com.billion_dollor_company.Bank_Server.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configs {

    @Bean
    public TransactionService getTransactionService() {
        return new TransactionService();
    }

//    @Bean
//    public TransactionInfoRepository getTransactionInfoRepository() {
//        return new TransactionInfoRepository() ;
//
//    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}
