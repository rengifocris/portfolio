package com.rengifo.core.portfolio.infrastructure.rest;

import com.rengifo.core.portfolio.application.finder.PortfolioFinder;
import com.rengifo.core.portfolio.application.finder.PortfolioFinderRequest;
import com.rengifo.core.portfolio.application.finder.PortfolioFinderResponse;
import com.rengifo.core.portfolio.application.finder.TimelineItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static com.rengifo.core.portfolio.shared.Constants.PORTFOLIO_ENDPOINT;

@Slf4j
@RestController
public class PortfolioGetController {

    private static final String GET_PORTFOLIO_LOG = "Get portfolio id: {}";

    private final PortfolioFinder portfolioFinder;

    public PortfolioGetController(
            PortfolioFinder portfolioFinder) {
        this.portfolioFinder = portfolioFinder;
    }

    @GetMapping(PORTFOLIO_ENDPOINT)
    public Response get(@PathVariable Long id) {
        log.info(GET_PORTFOLIO_LOG, id);

        return portfolioFinder.find(new PortfolioFinderRequest(id))
                .map(this::mapToResponse)
                .orElseThrow(PortfolioNotFoundException::new);
    }

    private Response mapToResponse(PortfolioFinderResponse portfolioFinderResponse) {
        var portfolioDto = portfolioFinderResponse.getPortfolioDto();

        var timeline = portfolioFinderResponse.getTimelineItems().stream()
                .map(this::mapToItem)
                .collect(Collectors.toList());

        return Response.builder()
                .id(portfolioDto.getId())
                .photo(portfolioDto.getImageUrl())
                .title(portfolioDto.getTitle())
                .description(portfolioDto.getDescription())
                .timeline(timeline)
                .twitterUsername(portfolioDto.getTwitterUsername())
                .build();
    }

    private Item mapToItem(TimelineItemDto itemDto) {
        return Item.builder()
                .id(itemDto.getId())
                .description(itemDto.getText())
                .name(itemDto.getName())
                .photo(itemDto.getImageUrl())
                .build();
    }

}
