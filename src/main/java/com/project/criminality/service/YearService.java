package com.project.criminality.service;

import com.project.criminality.model.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearService implements IYearService {

    /*
    @Autowired
    private YearRepository yearRepository;
    */

    @Autowired
    private YearRep yearRep;

    /*
    @Override
    public List<Year> findAllYears() {
        List<Year> years = yearRepository.findAllYears();
        return years;
    }
    */

    @Override
    public List<Year> findAllYearsByCrime(String tableName) {
        List<Year> years = yearRep.findAllYearsByCrime(tableName);
        return years;
    }
}
