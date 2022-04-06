package com.rengifo.core.portfolio.application.finder;

import com.rengifo.core.portfolio.domain.Portfolio;
import com.rengifo.core.portfolio.domain.PortfolioRepository;
import com.rengifo.core.portfolio.shared.PortfolioDto;
import com.rengifo.core.twitter.domain.Tweet;
import com.rengifo.core.twitter.domain.TwitterService;
import com.rengifo.core.twitter.infrastructure.client.TwitterClientException;
import com.rengifo.core.portfolio.domain.PortfolioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PortfolioFinderImpl implements PortfolioFinder {

    private static final String FIND_PORTFOLIO_LOG = "Find portfolio: {}";
    private static final String ERROR_TO_FIND_TIMELINE_LOG = "Error to find twitter post username: {}";

    private final PortfolioRepository portfolioRepository;
    private final TwitterService twitterService;

    public PortfolioFinderImpl(PortfolioRepository portfolioRepository,
                               TwitterService twitterService) {
        this.portfolioRepository = portfolioRepository;
        this.twitterService = twitterService;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PortfolioFinderResponse> find(PortfolioFinderRequest finderRequest) {
        log.debug(FIND_PORTFOLIO_LOG, finderRequest);

        var optionalPortfolio = portfolioRepository.findById(finderRequest.getId());
        if (optionalPortfolio.isPresent()) {

            final var portfolio = optionalPortfolio.get();
            final var timeline = findTwitterTimeline(portfolio.getTwitterUsername());
            return mapToFinderResponse(portfolio, timeline.orElse(Collections.emptyList()));
        }

        return Optional.empty();
    }

    private Optional<List<Tweet>> findTwitterTimeline(String twitterUsername) {
        Optional<List<Tweet>> twitterPosts;

        try {
            twitterPosts = twitterService.findByUsername(twitterUsername);
        } catch (TwitterClientException e) {
            log.error(ERROR_TO_FIND_TIMELINE_LOG, twitterUsername, e);
            twitterPosts = Optional.empty();
        }
        return twitterPosts;
    }

    private static Optional<PortfolioFinderResponse> mapToFinderResponse(Portfolio portfolio,
                                                                         List<Tweet> twitterPostList) {

        var portfolioDto = PortfolioDto.builder()
                .id(portfolio.getId())
                .title(portfolio.getTitle())
                .description(portfolio.getDescription())
                .imageUrl(portfolio.getImageUrl())
                .twitterUsername(portfolio.getTwitterUsername())
                .build();

        var timelineItemsDto = twitterPostList.stream()
                .map(post -> TimelineItemDto.builder()
                        .id(post.getId())
                        .imageUrl(post.getPhoto())
                        .name(post.getUserName())
                        .text(post.getText())
                        .build()
                ).collect(Collectors.toList());

        return Optional.of(new PortfolioFinderResponse(portfolioDto, timelineItemsDto));
    }
}
