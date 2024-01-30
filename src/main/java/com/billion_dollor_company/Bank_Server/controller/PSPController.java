package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.models.ResponseStatusInfo;
import com.billion_dollor_company.Bank_Server.models.TransactionRequest;
import com.billion_dollor_company.Bank_Server.models.UserInfoRequest;
import com.billion_dollor_company.Bank_Server.util.Constants;
import com.billion_dollor_company.Bank_Server.util.Helper;
import com.billion_dollor_company.Bank_Server.util.helpers.HelperJSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/psp")
public class PSPController {

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/transaction")
    public ResponseEntity<String> initiateTransaction(@RequestBody String jsonReqStr) {

        System.out.println("\n______________________________________AT PSP SERVER______________________________________\n");
        System.out.println("_________________________________PERFORMING TRANSACTIONS_________________________________\n");
        System.out.println("The JSON request sent from app to PSP Server is:\n" + Helper.getPrettyJson(jsonReqStr) + "\n\n");

        // json to pojo ya fir pojo to json
        ObjectMapper jsonMapper = new ObjectMapper();
        XmlMapper xmlMapper = new XmlMapper();

        // this try block converts json to xml. if some error happens, a bad_request response is sent back.

        // json -> pojo -> xml
        String reqInXML;
        try {
            // This converts the jsonString to a pojo(TransactionRequest)
            TransactionRequest reqObj = jsonMapper.readValue(jsonReqStr, TransactionRequest.class);
            // This converts the pojo to xml.
            reqInXML = xmlMapper.writer().withRootName("request").writeValueAsString(reqObj);
        } catch (Exception e) {
            System.out.println("An error while converting request to json or xml");
            e.printStackTrace();
            JSONObject statusObj = new JSONObject();
            statusObj.put(Constants.Tags.STATUS, Constants.Values.ERROR_WHILE_CONVERSION);
            return new ResponseEntity<>(statusObj.toString(), HttpStatus.OK);
        }

        System.out.println("The request being sent from PSP to NPCI Server in xml is : \n" + Helper.getPrettyXML(reqInXML, TransactionRequest.class) + "\n\n");

        String npciServerUrl = Constants.Servers.NPCI_Server.getTransactionURL();
        // this response will be in xml.
        ResponseEntity<String> responseFromNPCI = restTemplate.postForEntity(npciServerUrl, reqInXML, String.class);


        System.out.println("\n______________________________________AT PSP SERVER______________________________________\n");
        System.out.println("The response sent from NPCI to PSP is: \n" + Helper.getPrettyXML(responseFromNPCI.getBody(), ResponseStatusInfo.class));
        ResponseStatusInfo responseForAppObj = new ResponseStatusInfo();
        try {
            // xml to pojo.
            responseForAppObj = xmlMapper.readValue(responseFromNPCI.getBody(), ResponseStatusInfo.class);
        } catch (Exception ignored) {
            System.out.println("An error occurred while converting the xml status from NPCI to pojo.");
            responseForAppObj.setStatus(Constants.Transaction.Response.FAILED);

        }
        HttpStatus responseStatus = (HttpStatus) responseFromNPCI.getStatusCode();

        String responseForAppStr = "";
        try {
            responseForAppStr = jsonMapper.writeValueAsString(responseForAppObj);
        } catch (Exception e) {
        }
        System.out.println("The response being sent from PSP to App in JSON is \n" + Helper.getPrettyJson(responseForAppStr));
        return new ResponseEntity<>(responseForAppStr, responseStatus);
    }


    @RequestMapping("/userInfo")
    public ResponseEntity<String> getUserInfo(@RequestBody String jsonRequestStr) {

        System.out.println("\n______________________________________AT PSP SERVER______________________________________\n");
        System.out.println("____________________________________GETTING USER INFO____________________________________\n");

        System.out.println("The user info request at psp server in json is \n" + jsonRequestStr + "\n\n");
        ObjectMapper jsonMapper = new ObjectMapper();

        String upiID;
        try {
            upiID = jsonMapper.readValue(jsonRequestStr, UserInfoRequest.class).getUpiID();
        } catch (Exception e) {
            System.out.println("An error while converting request to json");
            e.printStackTrace();
            JSONObject statusObj = new JSONObject();
            statusObj.put(Constants.Tags.STATUS, Constants.Values.ERROR_WHILE_CONVERSION);
            return new ResponseEntity<>(statusObj.toString(), HttpStatus.OK);
        }

        System.out.println("Extracted upi id is " + upiID);
        Map<String, String> userInfoMap = Helper.getUserInfo(upiID);

        // we need to send back a response code. for the proper mapping of pojo objects.
        HttpStatus responseStatus = HttpStatus.OK;
        if (userInfoMap == null) {
            userInfoMap = new HashMap<>();
            userInfoMap.put(Constants.Tags.STATUS, Constants.Values.NO_RECORD_FOUND);
            responseStatus = HttpStatus.NOT_FOUND;
        }
        JSONObject resInJson = HelperJSON.convertMapToJSON(userInfoMap);
        System.out.println("The user info response at psp server in json is \n" + resInJson.toString() + "\n\n");

        ResponseEntity<String> response = new ResponseEntity<>(resInJson.toString(), responseStatus);
        return response;
    }

}

