package com.billion_dollor_company.Bank_Server.service.impl;

import com.billion_dollor_company.Bank_Server.payloads.listKeys.ListKeysReqDTO;
import com.billion_dollor_company.Bank_Server.service.interfaces.PublicKeysService;
import com.billion_dollor_company.Bank_Server.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class PublicKeysServiceImpl implements PublicKeysService {

    private final RestTemplate restTemplate;
    @Autowired
    public PublicKeysServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
//    @Override
//    public List<ListKeysReqDTO> getListKeys() {
//        String transactionURL = Constants.Servers.NPCI_Server.getListPublicKeys();
//
//        System.out.println("In Service Impl");
//        try {
//            return (List<ListKeysReqDTO>) restTemplate.getForEntity(transactionURL, ListKeysReqDTO.class).getBody();
//        } catch (HttpClientErrorException exception) {
//            // The body contains an object of TransactionResponseDTO in XML form.
//            return Collections.singletonList(exception.getResponseBodyAs(ListKeysReqDTO.class));
//        }
//    }

    public List<ListKeysReqDTO> getListKeys() {
        String transactionURL = Constants.Servers.NPCI_Server.getListPublicKeys();

        System.out.println("In Service Impl");

        ResponseEntity<List<ListKeysReqDTO>> responseEntity;
        try {
            responseEntity = restTemplate.exchange(transactionURL, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<ListKeysReqDTO>>() {});
            return responseEntity.getBody();
        } catch (HttpClientErrorException exception) {
            // If there's an error, return an empty list or handle it as needed
            System.err.println("HTTP Error: " + exception.getStatusCode());
            return Collections.emptyList();
        }
    }



}
