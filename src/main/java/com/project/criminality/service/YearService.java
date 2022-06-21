package com.project.criminality.service;

import com.project.criminality.model.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearService implements IYearService {

    @Autowired
    private YearRep yearRep;

    @Override
    public List<Year> findAllYearsByCrime(String tableName) {
        List<Year> years = yearRep.findAllYearsByCrime(tableName);
        return years;
    }
}
