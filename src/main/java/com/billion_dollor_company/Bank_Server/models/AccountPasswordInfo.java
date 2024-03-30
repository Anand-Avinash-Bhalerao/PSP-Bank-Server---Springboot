package com.billion_dollor_company.Bank_Server.models;


import com.billion_dollor_company.Bank_Server.util.Constants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = Constants.DatabaseTables.PASSWORDS_TABLE)
public class AccountPasswordInfo {

    @Id
    @Column(name = "upi_ID")
    private String upiID;

    @Column
    private String hashedPassword;

}
