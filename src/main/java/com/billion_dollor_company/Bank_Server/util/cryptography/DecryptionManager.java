package com.billion_dollor_company.Bank_Server.util.cryptography;

import com.billion_dollor_company.Bank_Server.util.Helper;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class DecryptionManager {
    private final String keyName;
    private PrivateKey privateKey;

    public DecryptionManager(String privateKey, String name) {
        this.keyName = name;
        initWithStrings(privateKey);
    }

    public void printKeys() {
        System.out.println("The private key named:'" + keyName + "'is :" + Helper.encode(privateKey.getEncoded()));
    }


    public void initWithStrings(String key) {
        try {
            PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(Helper.decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            privateKey = keyFactory.generatePrivate(keySpecPrivate);
        } catch (Exception ignored) {
        }
    }

    public String getDecryptedMessage(String encryptedMessage) {
        try {

            byte[] encryptedBytes = Helper.decode(encryptedMessage);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
            return new String(decryptedMessage, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }
}
