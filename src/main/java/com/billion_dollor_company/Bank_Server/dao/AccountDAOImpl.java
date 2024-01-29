package com.billion_dollor_company.Bank_Server.dao;

import com.billion_dollor_company.Bank_Server.models.AccountInfo;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private EntityManager entityManager;



    @Override
    public AccountInfo get(int id) {

        return null;
    }

    @Override
    public List<AccountInfo> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<AccountInfo> query = currentSession.createQuery("from users", AccountInfo.class);
        List<AccountInfo> list = query.getResultList();
        return list;
    }

    @Override
    public void save(AccountInfo info) {

    }
}
