package com.billion_dollor_company.Bank_Server.models;

public class TransactionRequest {
    private String payeeFullName;
    private String amountToTransfer;
    private String payeeAccountNo;
    private String payeeBankName;
    private String payerFullName;
    private String payerAccountNo;
    private String payerBankName;
    private String payerUpiID;
    private String encryptedPassword;
    private String payeeUpiID;

    public void setPayeeFullName(String payeeFullName){
        this.payeeFullName = payeeFullName;
    }

    public String getPayeeFullName(){
        return payeeFullName;
    }

    public void setAmountToTransfer(String amountToTransfer){
        this.amountToTransfer = amountToTransfer;
    }

    public String getAmountToTransfer(){
        return amountToTransfer;
    }

    public void setPayeeAccountNo(String payeeAccountNo){
        this.payeeAccountNo = payeeAccountNo;
    }

    public String getPayeeAccountNo(){
        return payeeAccountNo;
    }

    public void setPayeeBankName(String payeeBankName){
        this.payeeBankName = payeeBankName;
    }

    public String getPayeeBankName(){
        return payeeBankName;
    }

    public void setPayerFullName(String payerFullName){
        this.payerFullName = payerFullName;
    }

    public String getPayerFullName(){
        return payerFullName;
    }

    public void setPayerAccountNo(String payerAccountNo){
        this.payerAccountNo = payerAccountNo;
    }

    public String getPayerAccountNo(){
        return payerAccountNo;
    }

    public void setPayerBankName(String payerBankName){
        this.payerBankName = payerBankName;
    }

    public String getPayerBankName(){
        return payerBankName;
    }

    public void setPayerUpiID(String payerUpiID){
        this.payerUpiID = payerUpiID;
    }

    public String getPayerUpiID(){
        return payerUpiID;
    }

    public void setEncryptedPassword(String encryptedPassword){
        this.encryptedPassword = encryptedPassword;
    }

    public String getEncryptedPassword(){
        return encryptedPassword;
    }

    public void setPayeeUpiID(String payeeUpiID){
        this.payeeUpiID = payeeUpiID;
    }

    public String getPayeeUpiID(){
        return payeeUpiID;
    }

    @Override
    public String toString(){
        return
                "TransactionReq{" +
                        "payeeFullName = '" + payeeFullName + '\'' +
                        ",amountToTransfer = '" + amountToTransfer + '\'' +
                        ",payeeAccountNo = '" + payeeAccountNo + '\'' +
                        ",payeeBankName = '" + payeeBankName + '\'' +
                        ",payerFullName = '" + payerFullName + '\'' +
                        ",payerAccountNo = '" + payerAccountNo + '\'' +
                        ",payerBankName = '" + payerBankName + '\'' +
                        ",payerUpiID = '" + payerUpiID + '\'' +
                        ",encryptedPassword = '" + encryptedPassword + '\'' +
                        ",payeeUpiID = '" + payeeUpiID + '\'' +
                        "}";
    }
}
