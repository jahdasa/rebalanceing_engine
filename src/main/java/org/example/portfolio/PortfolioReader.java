package org.example.portfolio;

import org.example.portfolio.models.Portfolio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface PortfolioReader {

    Map<String, Portfolio> read() throws IOException;
}
