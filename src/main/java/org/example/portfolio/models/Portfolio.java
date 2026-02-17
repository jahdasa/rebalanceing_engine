package org.example.portfolio.models;

public class Portfolio {
    String ticker;
    double shares;
    double price;

    public Portfolio(String ticker, double shares, double price) {
        this.ticker = ticker;
        this.shares = shares;
        this.price = price;
    }

    public String getTicker() {
        return ticker;
    }

    public double getShares() {
        return shares;
    }

    public double getPrice() {
        return price;
    }
}
