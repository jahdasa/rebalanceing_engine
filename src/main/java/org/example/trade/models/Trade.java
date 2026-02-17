package org.example.trade.models;

public class Trade {
    String ticker;
    Side side;
    double tradeShares;
    double tradeValue;

    public Trade(String ticker, Side side, double tradeShares, double tradeValue) {
        this.ticker = ticker;
        this.side = side;
        this.tradeShares = tradeShares;
        this.tradeValue = tradeValue;
    }

    public Trade(String ticker) {
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }

    public Side getSide() {
        return side;
    }

    public double getTradeShares() {
        return tradeShares;
    }

    public double getTradeValue() {
        return tradeValue;
    }

    public enum Side {
        BUY,
        SELL,
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Trade trade = (Trade) o;
        return ticker.equals(trade.ticker);
    }

    @Override
    public int hashCode() {
        return ticker.hashCode();
    }
}