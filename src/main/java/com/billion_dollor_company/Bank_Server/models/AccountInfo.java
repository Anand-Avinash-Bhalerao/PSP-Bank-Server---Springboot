package com.billion_dollor_company.Bank_Server.models;


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
@Table(name = "users")
public class AccountInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

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

    @Column(name = "upi_ID")
    private String upiID;

    @Column
    private String encryptedPassword;

    @Column
    private String originalPassword;
}
