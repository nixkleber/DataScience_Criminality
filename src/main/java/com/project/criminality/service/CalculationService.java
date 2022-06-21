package com.project.criminality.service;

import com.project.criminality.model.Year;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CalculationService {
    final private List<Year> years;

    public CalculationService(List<Year> years) {
        this.years = years;
    }

    public int calculateTotalCasesForAgeRange(int ageRange){
        int totalCasesForAgeRange = 0;

        for (Year year : years
        ) {
            switch (ageRange) {
                case 0:
                    totalCasesForAgeRange += calculateFor10To60(year);
                    break;
                case 10:
                    totalCasesForAgeRange += calculateForUpTo10(year);
                    break;
                case 20:
                    totalCasesForAgeRange += calculateForUpTo20(year);
                    break;
                case 30:
                    totalCasesForAgeRange += calculateForUpTo30(year);
                    break;
                case 40:
                    totalCasesForAgeRange += calculateForUpTo40(year);
                    break;
                case 50:
                    totalCasesForAgeRange += calculateForUpTo50(year);
                    break;
                case 60:
                    totalCasesForAgeRange +=  calculateForUpTo60(year);
                    break;
            }
        }
        return totalCasesForAgeRange;
    }

    public int calculateTotalCasesForAgeRangeRegardingPopulation(int ageRange) throws IOException {

        HashMap<Integer, Integer> populationMap = XlsxService.getPopulationMapFromXlsx(ageRange);

        double totalCasesForAgeRange = 0;

        for (Year year : years
        ) {

            double populationThatYear = populationMap.get(year.getYear());

            switch (ageRange) {

                case 0:
                    totalCasesForAgeRange += calculateFor10To60(year);
                    break;
                case 10:
                    totalCasesForAgeRange += ((calculateForUpTo10(year))/populationThatYear) * 100000;
                    break;
                case 20:
                    totalCasesForAgeRange += ((calculateForUpTo20(year))/populationThatYear) * 100000;
                    break;
                case 30:
                    totalCasesForAgeRange += ((calculateForUpTo30(year))/populationThatYear) * 100000;
                    break;
                case 40:
                    totalCasesForAgeRange += ((calculateForUpTo40(year))/populationThatYear) * 100000;
                    break;
                case 50:
                    totalCasesForAgeRange += ((calculateForUpTo50(year))/populationThatYear) * 100000;
                    break;
                case 60:
                    totalCasesForAgeRange +=  ((calculateForUpTo60(year))/populationThatYear) * 100000;
                    break;
            }
        }
        return (int) totalCasesForAgeRange;
    }

    private int calculateForUpTo10(Year year) {
        int cases = 0;

        cases += year.getSunder6() + year.getS6to8() + year.getS8to10();

        return cases;
    }

    private int calculateForUpTo20(Year year) {
        int cases = 0;

        cases += year.getS10to12() + year.getS12to14() + year.getS14to16() + year.getS16to18() + year.getS18to21();

        return cases;
    }

    private int calculateForUpTo30(Year year) {
        int cases = 0;

        cases += year.getS21to23() + year.getS23to25() + year.getS25to30();

        return cases;
    }

    private int calculateForUpTo40(Year year) {
        int cases = 0;

        cases += year.getS30to40();

        return cases;
    }

    private int calculateForUpTo50(Year year) {
        int cases = 0;

        cases += year.getS40to50();

        return cases;
    }

    private int calculateForUpTo60(Year year) {
        int cases = 0;

        cases += year.getS50to60();

        return cases;
    }

    private int calculateFor10To60(Year year)
    {
        int cases = 0;

        cases += calculateForUpTo20(year) + calculateForUpTo30(year) + calculateForUpTo40(year) + calculateForUpTo50(year) + calculateForUpTo60(year);

        return cases;
    }

}

