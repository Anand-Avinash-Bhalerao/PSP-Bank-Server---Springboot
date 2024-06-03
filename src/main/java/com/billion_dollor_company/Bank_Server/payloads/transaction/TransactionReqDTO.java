package com.billion_dollor_company.Bank_Server.payloads.transaction;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "TransactionRequest")
public class TransactionReqDTO {

    @NotNull(message = "The payer upiID field cannot be empty")
    private String payerUpiID;

    @NotNull(message = "The payee upiID field cannot be empty")
    private String payeeUpiID;

    @NotNull(message = "The payer account no field cannot be empty")
    private String payerAccountNo;

    @NotNull(message = "The payer bank name field cannot be empty")
    private String payerBankName;

    @NotNull(message = "The amount to transfer field cannot be empty")
    private String amountToTransfer;

    @NotNull(message = "The encrypted password field cannot be empty")
    private String encryptedPassword;

}
