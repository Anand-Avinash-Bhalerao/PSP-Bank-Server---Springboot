package com.billion_dollor_company.Bank_Server.payloads.checkBalance;

import com.billion_dollor_company.Bank_Server.models.projections.BalanceInfoProjection;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "response")
public class BalanceResDTO {
    private String status;
    private String message;

    public BalanceResDTO(BalanceInfoProjection balanceInfo) {
        this.message = balanceInfo.getBalance();
    }
}
