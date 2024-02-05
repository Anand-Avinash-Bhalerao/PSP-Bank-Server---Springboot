
package com.billion_dollor_company.Bank_Server.repository;

import com.billion_dollor_company.Bank_Server.models.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TransactionInfoRepository extends JpaRepository<TransactionInfo,Long > {


    String findEncryptedPasswordByPayeeUpiID(String upiId);
}