package org.example.trade;

import org.example.trade.models.Trade;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface TradeWriter {

    void write(Set<Trade> trades) throws IOException;
}
