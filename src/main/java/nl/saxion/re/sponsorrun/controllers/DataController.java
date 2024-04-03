package nl.saxion.re.sponsorrun.controllers;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import nl.saxion.re.sponsorrun.model.MainWindow;
import nl.saxion.re.sponsorrun.model.Runner;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataController {


    private static ArrayList<String[]> readData(String csvFile, char separator, boolean skipRow) {
        try {
            // what a horrible syntax for configuring the csvparser. But ok.
            CSVReaderBuilder readerBuilder = new CSVReaderBuilder(new FileReader(csvFile));
            if (skipRow) {
                readerBuilder.withSkipLines(1);
            }
            CSVParserBuilder parserBuilder = new CSVParserBuilder();
            parserBuilder.withSeparator(separator);
            readerBuilder.withCSVParser(parserBuilder.build());
            CSVReader reader = readerBuilder.build();

            ArrayList<String[]> allLines = new ArrayList<>(reader.readAll());
            return allLines;
        } catch (IOException e) {

        }
        return new ArrayList<>();
    }


    public static void updateFromDisk() {
        // clear the list
        MainWindow.runnersList.clear();
        // read all rows and columns from the csv
        ArrayList<String[]> allRows = readData("runners.csv", ';', true);

        // create politician objects from all the rows/columns and add to the ArrayList.
        int i = 0;
        for (String[] row : allRows) {
            Runner p = new Runner();
            p.name = row[0];
            p.event = row[1];
            p.email = row[2];
            p.index = i;
            MainWindow.runnersList.add(p);
            i+=1;
        }
    }
}
