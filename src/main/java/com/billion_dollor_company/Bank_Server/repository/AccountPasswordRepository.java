package com.billion_dollor_company.Bank_Server.repository;

import com.billion_dollor_company.Bank_Server.models.AccountPasswordInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountPasswordRepository extends JpaRepository<AccountPasswordInfo, Integer> {
    AccountPasswordInfo findByUpiID(String upiID);
}
