package com.billion_dollor_company.Bank_Server.service.impl;


import com.billion_dollor_company.Bank_Server.exceptions.customExceptions.CheckBalanceFailedException;
import com.billion_dollor_company.Bank_Server.exceptions.customExceptions.TransactionFailedException;
import com.billion_dollor_company.Bank_Server.models.AccountInfo;
import com.billion_dollor_company.Bank_Server.models.AccountPasswordInfo;
import com.billion_dollor_company.Bank_Server.models.projections.BalanceInfoProjection;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceResDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionResDTO;
import com.billion_dollor_company.Bank_Server.repository.AccountInfoRepository;
import com.billion_dollor_company.Bank_Server.repository.AccountPasswordRepository;
import com.billion_dollor_company.Bank_Server.service.interfaces.BankService;
import com.billion_dollor_company.Bank_Server.service.interfaces.DatabasePWHashService;
import com.billion_dollor_company.Bank_Server.util.Constants;
import com.billion_dollor_company.Bank_Server.util.cryptography.DecryptionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankServiceImpl implements BankService {

    private final AccountInfoRepository accountInfoRepository;

    private final AccountPasswordRepository accountPasswordRepository;

    private final DatabasePWHashService databasePWHashService;

    @Autowired
    public BankServiceImpl(AccountInfoRepository accountInfoRepository, AccountPasswordRepository accountPasswordRepository, DatabasePWHashService databasePWHashService) {
        this.accountInfoRepository = accountInfoRepository;
        this.accountPasswordRepository = accountPasswordRepository;
        this.databasePWHashService = databasePWHashService;
    }

    private String getDecryptedPassword(String encryptedPWEntered) {
        // We decrypt the password.
        DecryptionManager decryptionManager = new DecryptionManager(Constants.Keys.BANK_PRIVATE_KEY, "Bank's private key");
        return decryptionManager.getDecryptedMessage(encryptedPWEntered);
    }

    private boolean isPasswordCorrect(String upiID, String encryptedPassword) {

        // This is the actual password which was entered. (Example: 123456)
        String pwEntered = getDecryptedPassword(encryptedPassword);

        // The pwEntered is a regular string but the correct passwords are stored in the DB by hashing them with SHA 256. We need to hash the entered password.
        String hashedPwEntered = databasePWHashService.getHashedPassword(pwEntered);

        // This is the correct password. payerAccountInfo was fetched from DB.
        AccountPasswordInfo payeeAccountPasswordInfo = accountPasswordRepository.findByUpiID(upiID);
        String hashedPwCorrect = payeeAccountPasswordInfo.getHashedPassword();

        return hashedPwEntered.equals(hashedPwCorrect);
    }


    @Override
    public BalanceResDTO getAccountBalance(BalanceReqDTO requestInfo) {
        String upiID = requestInfo.getUpiID();
        String encryptedPassword = requestInfo.getEncryptedPassword();

        BalanceResDTO responseInfo = new BalanceResDTO();
        responseInfo.setStatus(Constants.Transaction.Status.FAILED);
        if (!accountInfoRepository.existsByUpiID(upiID)) {
            responseInfo.setMessage(Constants.Values.NO_ACCOUNT_FOUND + upiID);
        } else if (!isPasswordCorrect(upiID, encryptedPassword)) {
            responseInfo.setMessage(Constants.Values.INCORRECT_PASSWORD);
        } else {
            BalanceInfoProjection projection = accountInfoRepository.getAccountBalanceByUpiID(upiID);
            responseInfo = new BalanceResDTO(projection);

            responseInfo.setStatus(Constants.Transaction.Status.SUCCESS);
            responseInfo.setMessage(Constants.Values.SUCCESSFUL_CHECK_BALANCE);
        }

        if (responseInfo.getStatus().equals(Constants.Transaction.Status.FAILED)) {
            throw new CheckBalanceFailedException(upiID, responseInfo.getMessage());
        }

        return responseInfo;
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public TransactionResDTO initiateTransaction(TransactionReqDTO requestInfo) {
        // Extract the upiID of payer and payee.
        // payer is the one paying and payee is the one receiving the money.
        String payerUpiID = requestInfo.getPayerUpiID();
        String payeeUpiID = requestInfo.getPayeeUpiID();

        // The account info of the payer and payee.
        AccountInfo payerAccountInfo = accountInfoRepository.findByUpiID(payerUpiID);
        AccountInfo payeeAccountInfo = accountInfoRepository.findByUpiID(payeeUpiID);

        //This is the response object that will be sent back to the client.
        TransactionResDTO responseInfo = new TransactionResDTO();

        // Initialize the status with failed. Update with success if everything goes correctly.
        responseInfo.setStatus(Constants.Transaction.Status.FAILED);

        // Check if both the users actually exist.
        if (payerAccountInfo == null) {
            responseInfo.setMessage(Constants.Values.NO_PAYER_ACCOUNT_FOUND);
        } else if (payeeAccountInfo == null) {
            responseInfo.setMessage(Constants.Values.NO_PAYEE_ACCOUNT_FOUND);
        } else {
            // Check if the password entered is correct or not.
            if (isPasswordCorrect(payerUpiID, requestInfo.getEncryptedPassword())) {

                float amountToPay = Float.parseFloat(requestInfo.getAmountToTransfer());
                float payerAccountBalance = Float.parseFloat(payerAccountInfo.getBalance());
                float payeeAccountBalance = Float.parseFloat(payeeAccountInfo.getBalance());

                // The payer should have sufficient bank balance.
                if (amountToPay <= payerAccountBalance) {

                    // New balance for payer and payee after credit and debit.
                    float newPayerBalance = payerAccountBalance - amountToPay;
                    float newPayeeBalance = payeeAccountBalance + amountToPay;

                    // perform the transaction. This need to be transactional. Either everything executes or nothing does.
                    // If anything goes wrong then an exception will be caught, and it will be rolled back.
                    try {
                        accountInfoRepository.updateBalance(String.valueOf(newPayerBalance), payerUpiID);
                        accountInfoRepository.updateBalance(String.valueOf(newPayeeBalance), payeeUpiID);
                        responseInfo.setStatus(Constants.Transaction.Status.SUCCESS);
                        responseInfo.setMessage(Constants.Values.SUCCESSFUL_PAYMENT);
                    } catch (Exception e) {
                        responseInfo.setMessage(Constants.Values.SOME_ERROR_OCCURRED + ". Transaction failed: " + e.getMessage());
                    }

                } else {
                    responseInfo.setMessage(Constants.Values.NOT_ENOUGH_BALANCE);
                }
            } else {
                responseInfo.setMessage(Constants.Values.INCORRECT_PASSWORD);
            }
        }

        if (responseInfo.getStatus().equals(Constants.Transaction.Status.FAILED)) {
            throw new TransactionFailedException(responseInfo.getMessage());
        }

        return responseInfo;
    }

}
