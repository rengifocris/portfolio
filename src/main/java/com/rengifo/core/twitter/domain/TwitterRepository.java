package com.rengifo.core.twitter.domain;

import java.util.List;

public interface TwitterRepository {

  List<Tweet> findByUserName(String userName);

}
