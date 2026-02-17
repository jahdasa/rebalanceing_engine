package org.example.target;

import org.agrona.collections.ObjectHashSet;
import org.example.portfolio.models.Portfolio;
import org.example.target.models.TargetModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class CsvModelReader implements ModelReader {
    private String filePath;

    public CsvModelReader(String filepath) {
        this.filePath = filepath;
    }

    @Override
    public Set<TargetModel> read() throws FileNotFoundException {
        var set = new ObjectHashSet<TargetModel>();
        var file = new File(filePath);
        try (Scanner inputStream = new Scanner(file)) {
            inputStream.next();
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                String[] values = data.split(",");

                String ticker = values[0];
                int targetPct = Integer.parseInt(values[1]);
                var targetModel = new TargetModel(ticker, targetPct);
                set.add(targetModel);
            }

            return set;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw e;
        }
    }
}
