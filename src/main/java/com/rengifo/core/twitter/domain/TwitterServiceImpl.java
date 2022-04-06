package com.rengifo.core.twitter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TwitterServiceImpl implements TwitterService {

  private static final String FIND_POSTS_LOG = "Find posts for userName: {}";

  private final TwitterRepository twitterRepository;

  public TwitterServiceImpl(TwitterRepository twitterRepository) {
    this.twitterRepository = twitterRepository;
  }

  @Override
  public Optional<List<Tweet>> findByUsername(String userName) {
    log.debug(FIND_POSTS_LOG, userName);

    return Optional.of(twitterRepository.findByUserName(userName));
  }
}
