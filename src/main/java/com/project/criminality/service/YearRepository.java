package com.project.criminality.service;

import com.project.criminality.model.Year;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YearRepository extends CrudRepository<Year, Integer> {
     @Query(nativeQuery = true, value="SELECT * FROM `000000`")
     List<Year> findAllYears();
}
