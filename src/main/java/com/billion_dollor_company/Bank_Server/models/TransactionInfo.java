package com.billion_dollor_company.Bank_Server.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "transactions")
public class TransactionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payeeFullName")
    private String payeeFullName;

    @Column(name = "amountToTransfer")
    private Double amountToTransfer;

    @Column(name = "payeeAccountNo")
    private String payeeAccountNo;

    @Column(name = "payeeBankName")
    private String payeeBankName;

    @Column(name = "payerFullName")
    private String payerFullName;

    @Column(name = "payerAccountNo")
    private String payerAccountNo;

    @Column(name = "payerBankName")
    private String payerBankName;

    @Column(name = "payerUpiID")
    private String payerUpiID;

    @Column(name = "encryptedPassword")
    private String encryptedPassword;

    @Column(name = "payeeUpiID")
    private String payeeUpiID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayeeFullName() {
        return payeeFullName;
    }

    public void setPayeeFullName(String payeeFullName) {
        this.payeeFullName = payeeFullName;
    }

    public Double getAmountToTransfer() {
        return amountToTransfer;
    }

    public void setAmountToTransfer(Double amountToTransfer) {
        this.amountToTransfer = amountToTransfer;
    }

    public String getPayeeAccountNo() {
        return payeeAccountNo;
    }

    public void setPayeeAccountNo(String payeeAccountNo) {
        this.payeeAccountNo = payeeAccountNo;
    }

    public String getPayeeBankName() {
        return payeeBankName;
    }

    public void setPayeeBankName(String payeeBankName) {
        this.payeeBankName = payeeBankName;
    }

    public String getPayerFullName() {
        return payerFullName;
    }

    public void setPayerFullName(String payerFullName) {
        this.payerFullName = payerFullName;
    }

    public String getPayerAccountNo() {
        return payerAccountNo;
    }

    public void setPayerAccountNo(String payerAccountNo) {
        this.payerAccountNo = payerAccountNo;
    }

    public String getPayerBankName() {
        return payerBankName;
    }

    public void setPayerBankName(String payerBankName) {
        this.payerBankName = payerBankName;
    }

    public String getPayerUpiID() {
        return payerUpiID;
    }

    public void setPayerUpiID(String payerUpiID) {
        this.payerUpiID = payerUpiID;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getPayeeUpiID() {
        return payeeUpiID;
    }

    public void setPayeeUpiID(String payeeUpiID) {
        this.payeeUpiID = payeeUpiID;
    }

    @Override
    public String toString() {
        return "TransactionInfo{" +
                "id=" + id +
                ", payeeFullName='" + payeeFullName + '\'' +
                ", amountToTransfer=" + amountToTransfer +
                ", payeeAccountNo='" + payeeAccountNo + '\'' +
                ", payeeBankName='" + payeeBankName + '\'' +
                ", payerFullName='" + payerFullName + '\'' +
                ", payerAccountNo='" + payerAccountNo + '\'' +
                ", payerBankName='" + payerBankName + '\'' +
                ", payerUpiID='" + payerUpiID + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                ", payeeUpiID='" + payeeUpiID + '\'' +
                '}';
    }
}
