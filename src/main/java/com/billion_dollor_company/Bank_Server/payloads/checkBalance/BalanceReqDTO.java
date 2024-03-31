package com.billion_dollor_company.Bank_Server.payloads.checkBalance;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceReqDTO {
    @NotEmpty(message = "The upiID field cannot be empty")
    private String upiID;

    @NotEmpty(message = "The encrypted password field cannot be empty")
    private String encryptedPassword;
}
