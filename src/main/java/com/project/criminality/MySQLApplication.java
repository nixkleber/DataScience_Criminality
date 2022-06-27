package com.project.criminality;

import com.project.criminality.model.Year;
import com.project.criminality.service.CalculationService;
import com.project.criminality.service.YearRep;
import com.project.criminality.service.YearService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class MySQLApplication implements CommandLineRunner {

    @Autowired
    YearService yearService;

    @Autowired
    YearRep yearRep;

    private static final String CRIMES_CSV_FILE = "./crimeCodes.csv";
    private static final String CATEGORY_CSV_FILE = "./crimeCategories.csv";

    public static void main(String[] args) {
        SpringApplication.run(MySQLApplication.class, args).close();
    }

    @Override
    public void run(String... args) throws IOException {
        printAllCrimeCodesToCSV();
        printCrimeCategoriesToCSV();
    }

    public void printAllCrimeCodesToCSV() throws IOException {
        List<String> crimeCodes = yearRep.getAllCrimeCodes();

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(CRIMES_CSV_FILE));

        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("crimeCode", "amount10UpTo21", "amount21UpTo30", "amount30UpTo40", "amount40UpTo50", "amount50UpTo60"));

        for (String crimeCode : crimeCodes
        ) {
            List<Year> years = yearService.findAllYearsByCrime(crimeCode);
            CalculationService calculationService = new CalculationService(years);

            System.out.println("CRIMECODE " + crimeCode);

            List<Integer> amountPerAgeRange = new ArrayList<>();

            for (int ageRange = 20; ageRange <= 60; ageRange = ageRange + 10) {
                int totalCasesForAgeRange = calculationService.calculateTotalCasesForAgeRangeRegardingPopulation(ageRange);

                System.out.println("Age: " + (ageRange - 10) + " -> " + (ageRange) + " : " + totalCasesForAgeRange);
                amountPerAgeRange.add(totalCasesForAgeRange);
            }
            System.out.println("----------------------------------------------------");

            if (!(crimeCode.matches("000000|100000|200000|300000|400000|500000|600000|700000"))) {
                csvPrinter.printRecord(crimeCode, amountPerAgeRange.get(0), amountPerAgeRange.get(1), amountPerAgeRange.get(2), amountPerAgeRange.get(3), amountPerAgeRange.get(4));
            }

            csvPrinter.flush();
        }
    }

    public void printCrimeCategoriesToCSV() throws IOException {
        List<String> crimeCodes = yearRep.getAllCrimeCodes();

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(CATEGORY_CSV_FILE));

        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("crimeCategoryCode", "crimeCategoryName", "amount10UpTo21", "amount21UpTo30", "amount30UpTo40", "amount40UpTo50", "amount50UpTo60"));


        HashMap<Integer, Integer> cat0AmountPerAgeRange = new HashMap<>();
        HashMap<Integer, Integer> cat1AmountPerAgeRange = new HashMap<>();
        HashMap<Integer, Integer> cat2AmountPerAgeRange = new HashMap<>();
        HashMap<Integer, Integer> cat3AmountPerAgeRange = new HashMap<>();
        HashMap<Integer, Integer> cat4AmountPerAgeRange = new HashMap<>();
        HashMap<Integer, Integer> cat5AmountPerAgeRange = new HashMap<>();
        HashMap<Integer, Integer> cat6AmountPerAgeRange = new HashMap<>();
        HashMap<Integer, Integer> cat7AmountPerAgeRange = new HashMap<>();


        for (int ageRange = 20; ageRange <= 60; ageRange = ageRange + 10) {
            cat0AmountPerAgeRange.put(ageRange, 0);
            cat1AmountPerAgeRange.put(ageRange, 0);
            cat2AmountPerAgeRange.put(ageRange, 0);
            cat3AmountPerAgeRange.put(ageRange, 0);
            cat4AmountPerAgeRange.put(ageRange, 0);
            cat5AmountPerAgeRange.put(ageRange, 0);
            cat6AmountPerAgeRange.put(ageRange, 0);
            cat7AmountPerAgeRange.put(ageRange, 0);
        }


        for (String crimeCode : crimeCodes
        ) {
            List<Year> years = yearService.findAllYearsByCrime(crimeCode);
            CalculationService calculationService = new CalculationService(years);

            System.out.println("CRIMECODE " + crimeCode);

            for (int ageRange = 20; ageRange <= 60; ageRange = ageRange + 10) {
                int totalCasesForAgeRange = calculationService.calculateTotalCasesForAgeRangeRegardingPopulation(ageRange);

                System.out.println("Age: " + (ageRange - 10) + " -> " + (ageRange) + " : " + totalCasesForAgeRange);

                if (crimeCode.startsWith("0") && !(crimeCode.equals("000000"))) {
                    cat0AmountPerAgeRange.replace(ageRange, cat0AmountPerAgeRange.get(ageRange) + totalCasesForAgeRange);
                } else if (crimeCode.startsWith("1") && !(crimeCode.equals("100000"))) {
                    cat1AmountPerAgeRange.replace(ageRange, cat1AmountPerAgeRange.get(ageRange) + totalCasesForAgeRange);
                } else if (crimeCode.startsWith("2") && !(crimeCode.equals("200000"))) {
                    cat2AmountPerAgeRange.replace(ageRange, cat2AmountPerAgeRange.get(ageRange) + totalCasesForAgeRange);
                } else if (crimeCode.startsWith("3") && !(crimeCode.equals("300000"))) {
                    cat3AmountPerAgeRange.replace(ageRange, cat3AmountPerAgeRange.get(ageRange) + totalCasesForAgeRange);
                } else if (crimeCode.startsWith("4") && !(crimeCode.equals("400000"))) {
                    cat4AmountPerAgeRange.replace(ageRange, cat4AmountPerAgeRange.get(ageRange) + totalCasesForAgeRange);
                } else if (crimeCode.startsWith("5") && !(crimeCode.equals("500000"))) {
                    cat5AmountPerAgeRange.replace(ageRange, cat5AmountPerAgeRange.get(ageRange) + totalCasesForAgeRange);
                } else if (crimeCode.startsWith("6") && !(crimeCode.equals("600000"))) {
                    cat6AmountPerAgeRange.replace(ageRange, cat6AmountPerAgeRange.get(ageRange) + totalCasesForAgeRange);
                } else if (crimeCode.startsWith("7") && !(crimeCode.equals("700000"))) {
                    cat7AmountPerAgeRange.replace(ageRange, cat7AmountPerAgeRange.get(ageRange) + totalCasesForAgeRange);
                }
            }

            System.out.println("----------------------------------------------------");
        }

        csvPrinter.printRecord("0", "Straftaten gegen das Leben", cat0AmountPerAgeRange.get(20), cat0AmountPerAgeRange.get(30), cat0AmountPerAgeRange.get(40), cat0AmountPerAgeRange.get(50), cat0AmountPerAgeRange.get(60));
        csvPrinter.printRecord("1", "Straftaten gegen die sexuelle Selbstbestimmung", cat1AmountPerAgeRange.get(20), cat1AmountPerAgeRange.get(30), cat1AmountPerAgeRange.get(40), cat1AmountPerAgeRange.get(50), cat1AmountPerAgeRange.get(60));
        csvPrinter.printRecord("2", "Rohheitsdelikte und Straftaten gegen die persönliche Freiheit", cat2AmountPerAgeRange.get(20), cat2AmountPerAgeRange.get(30), cat2AmountPerAgeRange.get(40), cat2AmountPerAgeRange.get(50), cat2AmountPerAgeRange.get(60));
        csvPrinter.printRecord("3", "Diebstahl ohne erschwerende Umstände", cat3AmountPerAgeRange.get(20), cat3AmountPerAgeRange.get(30), cat3AmountPerAgeRange.get(40), cat3AmountPerAgeRange.get(50), cat3AmountPerAgeRange.get(60));
        csvPrinter.printRecord("4", "Diebstahl unter erschwerenden Umständen", cat4AmountPerAgeRange.get(20), cat4AmountPerAgeRange.get(30), cat4AmountPerAgeRange.get(40), cat4AmountPerAgeRange.get(50), cat4AmountPerAgeRange.get(60));
        csvPrinter.printRecord("5", "Vermögens- und Fälschungsdelikte", cat5AmountPerAgeRange.get(20), cat5AmountPerAgeRange.get(30), cat5AmountPerAgeRange.get(40), cat5AmountPerAgeRange.get(50), cat5AmountPerAgeRange.get(60));
        csvPrinter.printRecord("6", "Sonstige Straftatbestände (StGB)", cat6AmountPerAgeRange.get(20), cat6AmountPerAgeRange.get(30), cat6AmountPerAgeRange.get(40), cat6AmountPerAgeRange.get(50), cat6AmountPerAgeRange.get(60));
        csvPrinter.printRecord("7", "Strafrechtliche Nebengesetze", cat7AmountPerAgeRange.get(20), cat7AmountPerAgeRange.get(30), cat7AmountPerAgeRange.get(40), cat7AmountPerAgeRange.get(50), cat7AmountPerAgeRange.get(60));

        csvPrinter.flush();
    }
}
