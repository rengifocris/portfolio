package com.rengifo.core.portfolio.application.finder;

import java.util.Optional;

public interface PortfolioFinder {
    Optional<PortfolioFinderResponse> find(PortfolioFinderRequest finderRequest);
}
