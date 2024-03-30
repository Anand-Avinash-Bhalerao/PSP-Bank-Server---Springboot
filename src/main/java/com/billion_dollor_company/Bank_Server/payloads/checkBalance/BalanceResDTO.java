package com.billion_dollor_company.Bank_Server.payloads.checkBalance;

import com.billion_dollor_company.Bank_Server.models.projections.BalanceInfoProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceResDTO {
    private String status;
    private String message;
    private String upiID;
    private String balance;

    public BalanceResDTO(BalanceInfoProjection balanceInfo) {
        this.upiID = balanceInfo.getUpiID();
        this.balance = balanceInfo.getBalance();
    }
}
