package com.project.criminality.service;

import com.project.criminality.model.Year;

import java.util.List;

public interface IYearService {

    List<Year> findAllYearsByCrime(String tableName);

}
