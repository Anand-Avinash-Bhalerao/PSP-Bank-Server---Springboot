package com.billion_dollor_company.Bank_Server.repository;

import com.billion_dollor_company.Bank_Server.models.AccountInfo;
import com.billion_dollor_company.Bank_Server.models.projections.AccountBasicProjection;
import com.billion_dollor_company.Bank_Server.models.projections.BalanceInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountInfoRepository extends JpaRepository<AccountInfo, String> {

    // This fetches only the required fields from the database.
    @Query("SELECT a.upiID as upiID, a.firstName as firstName, a.middleName as middleName, a.lastName as lastName, a.mobileNo as mobileNo FROM AccountInfo a WHERE a.upiID = :upiID")
    AccountBasicProjection getBasicInfoByUpiID(@Param("upiID") String upiID);

    @Query("SELECT a.upiID as upiID, a.balance as balance FROM AccountInfo a WHERE a.upiID = :upiID")
    BalanceInfoProjection getAccountBalanceByUpiID(@Param("upiID") String upiID);

    boolean existsByUpiID(String upiID);

    // Returns the entire record of a user. It contains: firstName, middleName, lastName, mobileNo, upiID, balance, accountNo
    AccountInfo findByUpiID(String upiID);

    // Used when a transaction is being made, and we need to update the bank balance.
    // It returns an int which indicates how many rows were affected.
    @Transactional
    @Modifying
    @Query(value = "update AccountInfo a set a.balance = :balance where a.upiID = :upiID")
    int updateBalance(@Param("balance") String balance, @Param("upiID") String upiID);

}
