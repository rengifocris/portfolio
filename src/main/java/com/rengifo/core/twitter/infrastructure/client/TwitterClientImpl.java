package com.rengifo.core.twitter.infrastructure.client;

import com.rengifo.core.twitter.domain.Tweet;
import com.rengifo.core.twitter.domain.TwitterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TwitterClientImpl implements TwitterRepository {

  private static final String TWITTER_TIMELINE_LOG = "Twitter timeline for: {}";
  private static final String ERROR_TO_REQUEST_LOG = "Error to request twitter timeline: {}";

  @Value("${tweets.timeline.size}")
  private int timelineSize;

  @Override
  public List<Tweet> findByUserName(String userName) {
    log.debug(TWITTER_TIMELINE_LOG, userName);

    try {
      Twitter singleton = TwitterFactory.getSingleton();
      var statusList = singleton.getUserTimeline(userName, new Paging(1, timelineSize));

      return statusList.stream()
          .map(this::mapToPost)
          .collect(Collectors.toList());

    } catch (TwitterException e) {
      log.error(ERROR_TO_REQUEST_LOG, userName, e);
      throw new TwitterClientException();
    }
  }

  private Tweet mapToPost(Status status) {
    return Tweet.builder()
        .userName(status.getUser().getName())
        .text(status.getText())
        .photo(status.getUser().getMiniProfileImageURL())
        .id(status.getId())
        .build();
  }
}
