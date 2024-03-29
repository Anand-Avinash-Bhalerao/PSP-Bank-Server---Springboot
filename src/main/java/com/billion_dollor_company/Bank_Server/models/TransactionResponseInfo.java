package com.billion_dollor_company.Bank_Server.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "TransactionResponse")
public class TransactionResponseInfo {
    private String status;
    private String message;
}
