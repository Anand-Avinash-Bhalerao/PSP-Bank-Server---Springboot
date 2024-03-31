package com.billion_dollor_company.Bank_Server.service.impl;

import com.billion_dollor_company.Bank_Server.service.interfaces.DatabasePWHashService;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;


// the passwords are stored in DB by hashing them first.
// this service can be used to hash a string.
@Service
public class DatabasePWHashServiceImpl implements DatabasePWHashService {
    @Override
    public String getHashedPassword(String password) {
        try {
            MessageDigest obj = MessageDigest.getInstance("SHA-256");
            //use update() method for passing data to the created MessageDigest Object
            obj.update(password.getBytes());
            //use the digest() method for computing the message digest
            byte[] byteArray = obj.digest();
            System.out.println(byteArray);
            //convert the byte array in to Hex String format
            StringBuffer hexData = new StringBuffer();
            for (int i = 0; i < byteArray.length; i++) {
                hexData.append(Integer.toHexString(0xFF & byteArray[i]));
            }
            String hashedDecryptedPassword = hexData.toString();
            return hashedDecryptedPassword;
        } catch (Exception e) {
        }
        return "Error";
    }
}
