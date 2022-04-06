package com.rengifo.core.twitter.domain;

import java.util.List;
import java.util.Optional;

public interface TwitterService {

  Optional<List<Tweet>> findByUsername(String userName);

}
