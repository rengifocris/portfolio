package com.rengifo.core.portfolio.application.updater;

import java.util.Optional;

public interface PortfolioUpdater {
    Optional<PortfolioUpdaterResponse> update(PortfolioUpdaterRequest portfolioUpdaterRequest);
}
