package com.billion_dollor_company.Bank_Server.models;

public class TransactionRequest {
    private String amount;

    private String encryptedString;

    private String payerID;

    private String bankName;

    private String payeeID;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEncryptedString() {
        return encryptedString;
    }

    public void setEncryptedString(String encryptedString) {
        this.encryptedString = encryptedString;
    }

    public String getPayerID() {
        return payerID;
    }

    public void setPayerID(String payerID) {
        this.payerID = payerID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPayeeID() {
        return payeeID;
    }

    public void setPayeeID(String payeeID) {
        this.payeeID = payeeID;
    }

    public TransactionRequest(){}


    @Override
    public String toString() {
        return "TransactionRequest{" +
                "amount='" + amount + '\'' +
                ", encryptedString='" + encryptedString + '\'' +
                ", payerID='" + payerID + '\'' +
                ", payeeID='" + payeeID + '\'' +
                ", bankName='" + bankName + '\'' +
                '}';
    }
}
