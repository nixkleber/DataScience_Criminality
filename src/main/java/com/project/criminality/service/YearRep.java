package com.project.criminality.service;

import com.project.criminality.model.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class YearRep {

    @Autowired
    EntityManager entityManager;

    public List<Year> findAllYearsByCrime(String tableName) {
        return (List<Year>) entityManager.createNativeQuery("SELECT * FROM `" + tableName +"`", Year.class).getResultList();
    }

    public List<String> getAllCrimeCodes()
    {
        return (List<String>) entityManager.createNativeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = SCHEMA()").getResultList();
    }

}
