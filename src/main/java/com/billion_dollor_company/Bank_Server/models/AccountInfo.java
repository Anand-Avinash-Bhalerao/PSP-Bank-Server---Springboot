package com.billion_dollor_company.Bank_Server.models;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class AccountInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer ID;

    @Column
    private String first_name;

    @Column
    private String middle_name;
    @Column
    private String last_name;


    @Column
    private String account_no;

    @Column
    private String balance;

    @Column
    private String mobile_no;

    @Column
    private String upi_id;

    @Column
    private String encrypted_password;


    @Column
    private String original_password;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getUpi_id() {
        return upi_id;
    }

    public void setUpi_id(String upi_id) {
        this.upi_id = upi_id;
    }

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encrypted_password) {
        this.encrypted_password = encrypted_password;
    }

    public String getOriginal_password() {
        return original_password;
    }

    public void setOriginal_password(String original_password) {
        this.original_password = original_password;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "ID=" + ID +
                ", first_name='" + first_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", account_no='" + account_no + '\'' +
                ", balance='" + balance + '\'' +
                ", mobile_no='" + mobile_no + '\'' +
                ", upi_id='" + upi_id + '\'' +
                ", encrypted_password='" + encrypted_password + '\'' +
                ", original_password='" + original_password + '\'' +
                '}';
    }
}
