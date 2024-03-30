package com.billion_dollor_company.Bank_Server.payloads;

import com.billion_dollor_company.Bank_Server.models.projections.AccountBasicProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountBasicDTO{
    private String upiID;

    private String firstName;

    private String middleName;

    private String lastName;

    private String accountNo;

    private String mobileNo;

    public AccountBasicDTO(AccountBasicProjection projection) {
        this.upiID = projection.getUpiID();
        this.firstName = projection.getFirstName();
        this.middleName = projection.getMiddleName();
        this.lastName = projection.getLastName();
        this.accountNo = projection.getAccountNo();
        this.mobileNo = projection.getMobileNo();
    }
}

