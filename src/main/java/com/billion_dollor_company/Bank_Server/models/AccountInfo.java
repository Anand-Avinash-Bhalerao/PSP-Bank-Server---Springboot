package com.billion_dollor_company.Bank_Server.models;


import com.billion_dollor_company.Bank_Server.util.Constants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = Constants.DatabaseTables.USERS_TABLE)
public class AccountInfo {

    @Id
    @Column(name = "upi_ID")
    private String upiID;

    @Column
    private String firstName;

    @Column
    private String middleName;

    @Column
    private String lastName;

    @Column
    private String accountNo;

    @Column
    private String balance;

    @Column
    private String mobileNo;

}
