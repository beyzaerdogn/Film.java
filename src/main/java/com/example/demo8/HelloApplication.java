package com.example.demo8;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


       String csvFilePath ="D:\\demo8\\src\\main\\java\\imdb_top_250 (1) - Kopya.csv";
        List<String[]> csvData = readCSV("D:\\demo8\\src\\main\\java\\imdb_top_250 (1) - Kopya.csv");
        if (csvData != null) {
            for (String[] row : csvData) {
                for (String cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        }
    }

    static List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}


