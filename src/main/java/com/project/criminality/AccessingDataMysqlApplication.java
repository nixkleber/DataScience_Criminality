package com.project.criminality;

import com.project.criminality.model.Year;
import com.project.criminality.service.CalculationService;
import com.project.criminality.service.YearRep;
import com.project.criminality.service.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AccessingDataMysqlApplication implements CommandLineRunner {

    @Autowired
    YearService yearService;

    @Autowired
    YearRep yearRep;

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataMysqlApplication.class, args).close();
    }

    @Override
    public void run(String... args) throws Exception {

        List<String> crimeCodes = yearRep.getAllCrimeCodes();
        System.out.println(yearRep.getAllCrimeCodes());

        for (String crimeCode : crimeCodes
        ) {
            List<Year> years = yearService.findAllYearsByCrime(crimeCode);
            CalculationService calculationService = new CalculationService(years);
            int totalCasesAges0To60 = calculationService.calculateTotalCasesForAgeRange(0);

            System.out.println("TOTAL CASES (0 -> 60) FOR CRIMECODE " + crimeCode + ": " + totalCasesAges0To60 + "\n");

            for (int ageRange = 10; ageRange <= 60; ageRange = ageRange + 10) {
                System.out.println("Age: " + (ageRange - 10) + " -> " + (ageRange) + " : " + calculationService.calculateTotalCasesForAgeRange(ageRange));
            }
            System.out.println("----------------------------------------------------");
        }

    }
}
