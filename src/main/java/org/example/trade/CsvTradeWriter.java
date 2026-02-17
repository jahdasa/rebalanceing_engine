package org.example.trade;

import org.example.trade.models.Trade;

import java.io.*;
import java.util.Set;

public class CsvTradeWriter implements TradeWriter {

    @Override
    public void write(Set<Trade> trades) throws FileNotFoundException {
        var file = new File("./trades.csv");
        try (PrintWriter writer = new PrintWriter(file)) {
            trades.stream().forEach(trade -> {
                String line = trade.getTicker() + ","
                        + trade.getSide() + ","
                        + trade.getTradeShares() + ","
                        + trade.getTradeValue();

                writer.println(line);
            });
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw e;
        }
    }
}
