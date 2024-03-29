package com.billion_dollor_company.Bank_Server.repository;

import com.billion_dollor_company.Bank_Server.models.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BankRepository extends JpaRepository<AccountInfo, Integer> {
    AccountInfo findByUpiID(String upiID);

    @Transactional
    @Modifying
    @Query(
            value = "update users u set u.balance = :balance where u.upi_ID = :upiID",
            nativeQuery = true
    )
    int updateBalance(@Param("balance") String balance, @Param("upiID") String upiID);

    boolean existsByUpiID(String upiID);

}
