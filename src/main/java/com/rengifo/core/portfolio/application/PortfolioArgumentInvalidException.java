package com.rengifo.core.portfolio.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PortfolioArgumentInvalidException extends RuntimeException {
    public PortfolioArgumentInvalidException(String message) {
        super(message);
    }
}
