package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.util.Constants;
import com.billion_dollor_company.Bank_Server.util.Helper;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/psp")
public class PSPController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/transaction")
    public String forwardAhead(@RequestBody String request) {
        System.out.println("The req in json is \n" + request+"\n\n");

        String reqInXML = Helper.jsonToXml(request);
        System.out.println("The req forwarded ahead in xml is \n" + reqInXML+"\n\n");
        // todo: we need to add signature checking here.

        // send the request forward. It will now send the request to NPCI.
        String npciServerUrl = Constants.Servers.NPCI_Server.getTransactionURL();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(npciServerUrl, reqInXML, String.class);

        // we need to send the response back to the app. the response needs to be in json.
        // map to store the response and then this map is converted into json.
        Map<String, String> map = new HashMap<>();

        if (responseEntity.getStatusCode().is2xxSuccessful()) {

            // this response is in xml. status need to be extracted.
            String responseXML = responseEntity.getBody();
            String response = Helper.extractFromXML(responseXML, "status");
            if (response == null) response = "failed";
            map.put("status", response);
        } else {
            map.put("status", "failed");
        }

        JSONObject json = new JSONObject(map);
        return json.toString();
    }

    @RequestMapping("/checkbalance")
    public String forwardAhead2(@RequestBody String request) {
        System.out.println("The req in json is \n" + request+"\n\n");

        String reqInXML = Helper.jsonToXml(request);
        System.out.println("The req forwarded ahead in xml is \n" + reqInXML+"\n\n");
        // todo: we need to add signature checking here.

        // send the request forward. It will now send the request to NPCI.
        String npciServerUrl = Constants.Servers.NPCI_Server.getCheckBalanceURL();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(npciServerUrl, reqInXML, String.class);

        // we need to send the response back to the app. the response needs to be in json.
        // map to store the response and then this map is converted into json.
        Map<String, String> map = new HashMap<>();

        if (responseEntity.getStatusCode().is2xxSuccessful()) {

            // this response is in xml. status need to be extracted.
            String responseXML = responseEntity.getBody();
            System.out.println("Response in PSP "+responseEntity);
             String response = Helper.extractFromXML(responseXML, "status");
            String balance = Helper.extractFromXML(responseXML, "balance");
        if (response == null) response = "failed";
        map.put("status", response);
        map.put("balance", balance);
    }else {
            map.put("status", "failed");
        }

        System.out.println("PSP response received from bank "+map);
        JSONObject json = new JSONObject(map);
        return json.toString();
    }

}
