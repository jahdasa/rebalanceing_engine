package org.example.trade;

import org.example.portfolio.models.Portfolio;
import org.example.target.models.TargetModel;
import org.example.trade.models.Trade;

import java.util.Map;
import java.util.Set;

public interface TradeComputer {

    Set<Trade> compute(Map<String, Portfolio> portfolios, Set<TargetModel> models);
}
