package com.billion_dollor_company.Bank_Server.payloads;

import com.billion_dollor_company.Bank_Server.models.projections.AccountBasicProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountBasicDTO{

    @NotEmpty(message = "The upiID field cannot be empty.")
    private String upiID;

    private String firstName;

    private String middleName;

    private String lastName;

    private String accountNo;

    private String bankName;

    private String mobileNo;

    public AccountBasicDTO(AccountBasicProjection projection) {
        this.upiID = projection.getUpiID();
        this.firstName = projection.getFirstName();
        this.middleName = projection.getMiddleName();
        this.lastName = projection.getLastName();
        this.accountNo = projection.getAccountNo();
        this.mobileNo = projection.getMobileNo();
        this.bankName = projection.getBankName();
    }
}

