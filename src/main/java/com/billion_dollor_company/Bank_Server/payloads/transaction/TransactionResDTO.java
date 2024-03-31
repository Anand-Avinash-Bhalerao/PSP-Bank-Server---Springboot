package com.billion_dollor_company.Bank_Server.payloads.transaction;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "TransactionResponse")
@Builder
public class TransactionResDTO {
    public String status;
    public String message;
}
