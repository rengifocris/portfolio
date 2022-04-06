package com.rengifo.core.portfolio.infrastructure.persistence;

import com.rengifo.core.portfolio.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepositoryJpa extends JpaRepository<Portfolio, Long> {

}
