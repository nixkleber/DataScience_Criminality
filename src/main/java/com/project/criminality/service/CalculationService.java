package com.project.criminality.service;

import com.project.criminality.model.Year;

import java.util.List;

public class CalculationService {
    final private List<Year> years;

    public CalculationService(List<Year> years) {
        this.years = years;
    }

    public int calculateTotalCasesForAgeRange(int ageRange) {

        int totalCasesForAgeRange = 0;

        for (Year year : years
        ) {
            switch (ageRange) {

                case 0:
                    totalCasesForAgeRange += calculateFor0To60(year);

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

    private int calculateForUpTo10(Year year) {
        int casesThatYear = 0;

        casesThatYear += year.getSunder6() + year.getS6to8() + year.getS8to10();

        return casesThatYear;
    }

    private int calculateForUpTo20(Year year) {
        int casesThatYear = 0;

        casesThatYear += year.getS10to12() + year.getS12to14() + year.getS14to16() + year.getS16to18() + year.getS18to21();

        return casesThatYear;
    }

    private int calculateForUpTo30(Year year) {
        int casesThatYear = 0;

        casesThatYear += year.getS21to23() + year.getS23to25() + year.getS25to30();

        return casesThatYear;
    }

    private int calculateForUpTo40(Year year) {
        int casesThatYear = 0;

        casesThatYear += year.getS30to40();

        return casesThatYear;
    }

    private int calculateForUpTo50(Year year) {
        int casesThatYear = 0;

        casesThatYear += year.getS40to50();

        return casesThatYear;
    }

    private int calculateForUpTo60(Year year) {
        int casesThatYear = 0;

        casesThatYear += year.getS50to60();

        return casesThatYear;
    }

    private int calculateFor0To60(Year year)
    {
        int casesThatYear = 0;

        casesThatYear += calculateForUpTo10(year) + calculateForUpTo20(year) + calculateForUpTo30(year) + calculateForUpTo40(year) + calculateForUpTo50(year) + calculateForUpTo60(year);

        return casesThatYear;
    }

}

