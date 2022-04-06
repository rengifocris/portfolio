package com.rengifo.core.portfolio.infrastructure.rest;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
class Response {

    private Long id;
    private String photo;
    private String title;
    private String description;
    private String twitterUsername;
    private List<Item> timeline;
}
