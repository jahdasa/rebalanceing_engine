package org.example;

import org.example.portfolio.CsvPortfolioReader;
import org.example.portfolio.PortfolioReader;
import org.example.target.CsvModelReader;
import org.example.target.ModelReader;
import org.example.trade.CsvTradeWriter;
import org.example.trade.DefaultTradeComputer;
import org.example.trade.TradeComputer;
import org.example.trade.TradeWriter;

import java.io.IOException;

public class Application {
    private final PortfolioReader portfolioReader = new CsvPortfolioReader("./portfolio.csv");
    private final ModelReader modelReader = new CsvModelReader("./target.csv");
    private final TradeWriter tradeWriter = new CsvTradeWriter();
    private final TradeComputer tradeComputer = new DefaultTradeComputer();

    public void start() throws IOException {
        var portfolios = portfolioReader.read();
        var targetModels = modelReader.read();
        var trades = tradeComputer.compute(portfolios, targetModels);
        tradeWriter.write(trades);
    }
}
