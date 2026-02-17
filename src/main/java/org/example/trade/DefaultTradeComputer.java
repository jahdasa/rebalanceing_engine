package org.example.trade;

import org.agrona.collections.ObjectHashSet;
import org.example.portfolio.models.Portfolio;
import org.example.target.models.TargetModel;
import org.example.trade.models.Trade;

import java.util.Map;
import java.util.Set;

import static org.example.trade.models.Trade.Side.BUY;
import static org.example.trade.models.Trade.Side.SELL;

public class DefaultTradeComputer implements TradeComputer {

    @Override
    public Set<Trade> compute(Map<String, Portfolio> portfolios, Set<TargetModel> models) {
        var portfolioTotalValue = portfolios.values()
                .stream()
                .mapToDouble(portfolio -> portfolio.getShares() * portfolio.getPrice())
                .sum();

        Set<Trade> trades = new ObjectHashSet<>();

        models.forEach(model -> {
            if (portfolios.containsKey(model.getTicker())) {
                var value = portfolios.get(model.getTicker());

                var tickerValueAfterTrading = portfolioTotalValue * model.getTargetPct() / 100;
                var tickerShareAfterTrading = tickerValueAfterTrading / value.getPrice();

                var sharesToBuyOrSell = value.getShares() - tickerShareAfterTrading;
                var side = sharesToBuyOrSell > 0 ? SELL : BUY;
                trades.add(new Trade(model.getTicker(), side, Math.abs(sharesToBuyOrSell),
                        Math.abs(sharesToBuyOrSell) * value.getPrice()));
            }
        });

        portfolios.keySet().forEach(key -> {
            if (!trades.contains(new Trade(key))) {
                var value = portfolios.get(key);
                trades.add(new Trade(key, SELL, value.getShares(), value.getPrice() * value.getShares()));
            }
        });

        return trades;
    }
}
